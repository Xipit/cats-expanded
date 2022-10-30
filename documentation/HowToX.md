# Documentation to help me remember how i do stuff

## New Catear Variants

1. use Blockbench to create a new model texture. (use "catear_model_blockbench.bbmodel")
   1. put texture in assets/catsexpanded/textures/models
2. use Krita to create item texture
   1. open existing texture and modify
   2. open model texture to extract colours
   3. put texture in assets/catsexpanded/textures/item
3. Create .json model (modify existing one)
   1. put in assets/catsexpanded/models/item
4. Create new recipe (modify existing one)
   1. put in data/catsexpanded/recipes
5. In ModItems.java create new CATEARARMOR instance
   1. like
      this: ```public static final CatearArmor CATEARS_CYAN = new CatearArmor("cyan", EquipmentSlot.HEAD, CATEAR_GROUP);```
6. In ModItems.java register new item in register() method
   1. like this: ```RegistryHelper.registerItem("catears_cyan", CATEARS_CYAN);```

## Create new Release

1. update Version number
   1. in src/fabric.mod.json under ```"version": "1.2.1+1.19.0"```
   2. in gradle.properties under ```mod_version = 1.2.1+1.19.0```
   3. in .github/worklfows/publish.yml under ```VERSION: 1.2.1+1.19.0```
      and ```  RELEASE_NAME: Cats Expanded 1.2.1 for Minecraft 1.19.0```
2. update changelog in CHANGELOG.md
3. Merge pull request into current default branch