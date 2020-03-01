package tfar.leavesdecayonotherleaves;

import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.block.LeavesBlock.DISTANCE;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LeavesDecayOnOtherLeaves.MODID)
public class LeavesDecayOnOtherLeaves {
    public static final String MODID = "leavesdecayonotherleaves";

    public static int getDistance(BlockState state, BlockState neighbor) {
        if (BlockTags.LOGS.contains(neighbor.getBlock())) {
            return 0;
        } else {
            return neighbor.getBlock() == state.getBlock() ? neighbor.get(DISTANCE) : 7;
        }
    }

    public static BlockState handleDecay(BlockState state, IWorld worldIn, BlockPos pos) {
        int i = 7;
        try (
                BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
            for (Direction direction : Direction.values()) {
                blockpos$pooledmutable.setPos(pos).move(direction);
                i = Math.min(i, getDistance(state,worldIn.getBlockState(blockpos$pooledmutable)) + 1);
                if (i == 1) {
                    break;
                }
            }
        }
        return state.with(DISTANCE, i);
    }
}
