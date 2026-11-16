package net.createcobblestone.neoforge.data;

import java.util.ArrayList;
import java.util.List;
import net.createcobblestone.neoforge.index.Network;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import org.jetbrains.annotations.NotNull;

public record GeneratorTypesPayload(List<GeneratorTypesPayload.GeneratorTypePayload> list) implements CustomPacketPayload {
   public static final Type<GeneratorTypesPayload> TYPE = new Type(Network.GENERATOR_TYPES_PACKET);
   public static final StreamCodec<FriendlyByteBuf, GeneratorTypesPayload> STREAM_CODEC = StreamCodec.of(
      GeneratorTypesPayload::encode, GeneratorTypesPayload::decode
   );

   @NotNull
   public Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   private static void encode(FriendlyByteBuf buf, GeneratorTypesPayload payload) {
      List<GeneratorTypesPayload.GeneratorTypePayload> l = payload.list;
      buf.writeVarInt(l.size());

      for (GeneratorTypesPayload.GeneratorTypePayload gt : l) {
         GeneratorTypesPayload.GeneratorTypePayload.STREAM_CODEC.encode(buf, gt);
      }
   }

   private static GeneratorTypesPayload decode(FriendlyByteBuf buf) {
      int n = buf.readVarInt();
      List<GeneratorTypesPayload.GeneratorTypePayload> out = new ArrayList<>(n);

      for (int i = 0; i < n; i++) {
         out.add((GeneratorTypesPayload.GeneratorTypePayload)GeneratorTypesPayload.GeneratorTypePayload.STREAM_CODEC.decode(buf));
      }

      return new GeneratorTypesPayload(out);
   }

   public record GeneratorTypePayload(String id, String block, int stress, float ratio, int storage) {
      public static final StreamCodec<FriendlyByteBuf, GeneratorTypesPayload.GeneratorTypePayload> STREAM_CODEC = StreamCodec.of((buf, v) -> {
         buf.writeUtf(v.id);
         buf.writeUtf(v.block);
         buf.writeVarInt(v.stress);
         buf.writeFloat(v.ratio);
         buf.writeVarInt(v.storage);
      }, buf -> new GeneratorTypesPayload.GeneratorTypePayload(buf.readUtf(), buf.readUtf(), buf.readVarInt(), buf.readFloat(), buf.readVarInt()));
   }
}
