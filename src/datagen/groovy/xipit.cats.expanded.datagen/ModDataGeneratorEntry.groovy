package xipit.cats.expanded.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import xipit.cats.expanded.CatsExpandedMod
import xipit.cats.expanded.datagen.worldgen.ModWorldGenBootstrap
import xipit.cats.expanded.datagen.worldgen.ModWorldGenProvider


// see https://github.com/Ayutac/fabric-example-worldgen/blob/1.19.3/src/datagen/groovy/net/fabricmc/example/datagen/ExampleModDataGeneratorEntry.groovy
class ModDataGeneratorEntry implements DataGeneratorEntrypoint {
    @Override
    void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        def pack = fabricDataGenerator.createPack()
        def add = { FabricDataGenerator.Pack.RegistryDependentFactory factory ->
            pack.addProvider(factory)
        }   // Groovy closure -> https://www.tutorialspoint.com/groovy/groovy_closures.htm

        add ModWorldGenProvider::new
    }

    @Override
    void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModWorldGenBootstrap::configuredFeatures)
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModWorldGenBootstrap::placedFeatures)
    }

    @Override
    String getEffectiveModId() {
        return CatsExpandedMod.MOD_ID
    }
}
