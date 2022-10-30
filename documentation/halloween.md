-> Carved Pumpkin with Cat face

    public static final Block CARVED_PUMPKIN = Blocks.register("carved_pumpkin", new CarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(Blocks::always)));
    public static final Block JACK_O_LANTERN = Blocks.register("jack_o_lantern", new CarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0f).sounds(BlockSoundGroup.WOOD).luminance(state -> 15).allowsSpawning(Blocks::always)));