import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Rewrites mixin annotations whose "at" member must be a single @At (Redirect, ModifyArg, ModifyArgs,
 * ModifyVariable) but were emitted as a one-element array. MixinExtras casts that value to AnnotationNode
 * and crashes with ClassCastException(ArrayList -> AnnotationNode).
 * Usage: java FixAt <in.jar> <out.jar> [--dry]
 */
public class FixAt {
    static final Set<String> SINGLE_AT = Set.of(
        "Lorg/spongepowered/asm/mixin/injection/Redirect;",
        "Lorg/spongepowered/asm/mixin/injection/ModifyArg;",
        "Lorg/spongepowered/asm/mixin/injection/ModifyArgs;",
        "Lorg/spongepowered/asm/mixin/injection/ModifyVariable;");

    static int fixes = 0;

    static boolean fixList(List<AnnotationNode> anns, String where) {
        if (anns == null) return false;
        boolean changed = false;
        for (AnnotationNode a : anns) {
            if (!SINGLE_AT.contains(a.desc) || a.values == null) continue;
            for (int i = 0; i < a.values.size(); i += 2) {
                if ("at".equals(a.values.get(i)) && a.values.get(i + 1) instanceof List<?> l) {
                    if (l.size() == 1) {
                        a.values.set(i + 1, l.get(0));
                        changed = true; fixes++;
                        System.out.println("fixed " + a.desc.substring(a.desc.lastIndexOf('/') + 1) + " in " + where);
                    } else {
                        System.out.println("WARN multi-at " + a.desc + " in " + where + " size=" + l.size());
                    }
                }
            }
        }
        return changed;
    }

    public static void main(String[] args) throws Exception {
        boolean dry = args.length > 2 && args[2].equals("--dry");
        try (ZipFile in = new ZipFile(args[0]);
             ZipOutputStream out = dry ? null : new ZipOutputStream(new FileOutputStream(args[1]))) {
            for (Enumeration<? extends ZipEntry> e = in.entries(); e.hasMoreElements();) {
                ZipEntry ze = e.nextElement();
                byte[] data = in.getInputStream(ze).readAllBytes();
                if (ze.getName().endsWith(".class")) {
                    ClassNode cn = new ClassNode();
                    new ClassReader(data).accept(cn, 0);
                    boolean changed = false;
                    for (MethodNode m : cn.methods) {
                        String w = cn.name + "." + m.name;
                        changed |= fixList(m.invisibleAnnotations, w);
                        changed |= fixList(m.visibleAnnotations, w);
                    }
                    if (changed) {
                        ClassWriter cw = new ClassWriter(0);
                        cn.accept(cw);
                        data = cw.toByteArray();
                    }
                }
                if (!dry) {
                    ZipEntry o = new ZipEntry(ze.getName());
                    o.setTime(ze.getTime());
                    out.putNextEntry(o);
                    if (!ze.isDirectory()) out.write(data);
                    out.closeEntry();
                }
            }
        }
        System.out.println("total fixes: " + fixes);
    }
}
