package xipit.cats.expanded.world.modifiers;

import java.util.Random;
import java.util.stream.Stream;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;

public class ModSmallSquarePlacementModifier extends SquarePlacementModifier{
    // a smaller SquarePlacementModifier
    // only 8 blocks max. from center, instead of 16

    @Override
    public Stream<BlockPos> getPositions(DecoratorContext context, Random random, BlockPos pos) {
        int i = random.nextInt(8) + pos.getX();
        int j = random.nextInt(8) + pos.getZ();
        return Stream.of(new BlockPos(i, pos.getY(), j));
    }
}
