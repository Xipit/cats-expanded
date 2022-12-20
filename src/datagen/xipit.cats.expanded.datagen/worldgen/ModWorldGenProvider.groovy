package xipit.cats.expanded.datagen.worldgen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper

import java.util.concurrent.CompletableFuture
// see https://github.com/Ayutac/fabric-example-worldgen/blob/1.19.3/src/datagen/groovy/net/fabricmc/example/datagen/worldgen/ExampleModWorldGenProvider.groovy
class ModWorldGenProvider extends FabricDynamicRegistryProvider {

    ModWorldGenProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture)
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE))
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE))
    }

    @Override
    String getName() {
        return "Cats Expanded Mod World Gen"
    }
}