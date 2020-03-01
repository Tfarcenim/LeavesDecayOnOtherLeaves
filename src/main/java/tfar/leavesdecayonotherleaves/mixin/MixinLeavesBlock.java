package tfar.leavesdecayonotherleaves.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.leavesdecayonotherleaves.LeavesDecayOnOtherLeaves;

import static net.minecraft.block.LeavesBlock.DISTANCE;

@Mixin(LeavesBlock.class)
public abstract class MixinLeavesBlock {

	@Inject(method = "updateDistance",at = @At("RETURN"),cancellable = true)
	private static void exactMatch(BlockState state, IWorld worldIn, BlockPos pos, CallbackInfoReturnable<BlockState> cir){
		if (cir.getReturnValue().get(DISTANCE) == 7)return;
		cir.setReturnValue(LeavesDecayOnOtherLeaves.handleDecay(state,worldIn,pos));
	}
}
