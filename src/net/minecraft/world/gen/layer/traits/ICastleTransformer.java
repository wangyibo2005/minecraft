package net.minecraft.world.gen.layer.traits;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;

public interface ICastleTransformer extends IAreaTransformer1, IDimOffset1Transformer {
   int apply(INoiseRandom p_202748_1_, int p_202748_2_, int p_202748_3_, int p_202748_4_, int p_202748_5_, int p_202748_6_);

   default int applyPixel(IExtendedNoiseRandom<?> p_215728_1_, IArea p_215728_2_, int p_215728_3_, int p_215728_4_) {
      return this.apply(p_215728_1_, p_215728_2_.get(this.getParentX(p_215728_3_ + 1), this.getParentY(p_215728_4_ + 0)), p_215728_2_.get(this.getParentX(p_215728_3_ + 2), this.getParentY(p_215728_4_ + 1)), p_215728_2_.get(this.getParentX(p_215728_3_ + 1), this.getParentY(p_215728_4_ + 2)), p_215728_2_.get(this.getParentX(p_215728_3_ + 0), this.getParentY(p_215728_4_ + 1)), p_215728_2_.get(this.getParentX(p_215728_3_ + 1), this.getParentY(p_215728_4_ + 1)));
   }
}