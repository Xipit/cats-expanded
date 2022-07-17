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
4. In ModItems.java create new CATEARARMOR instance 
   1. like this: ```public static final CatearArmor CATEARS_CYAN    = new CatearArmor("cyan", EquipmentSlot.HEAD, CATEAR_GROUP);```
5. In ModItems.java register new item in register() method
   1. like this: ```RegistryHelper.registerItem("catears_cyan", CATEARS_CYAN);```