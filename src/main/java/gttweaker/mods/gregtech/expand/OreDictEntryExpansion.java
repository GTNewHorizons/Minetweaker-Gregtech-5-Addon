package gttweaker.mods.gregtech.expand;

import java.util.*;

import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.oredict.IOreDictEntry;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenGetter;

import static gregtech.api.enums.GT_Values.MOD_ID;

import minetweaker.MineTweakerAPI;

@ZenExpansion("minetweaker.oredict.IOreDictEntry")
@ModOnly(MOD_ID)
public class OreDictEntryExpansion {
	@ZenGetter("greggedItem")
	public static IItemStack getGreggedItem(IOreDictEntry oreDictEntry) {
        List<IItemStack> matchedItems = new ArrayList<IItemStack>();
        
        for (IItemStack item : oreDictEntry.getItems()) {
            //TODO: how to determine GT's items better?
            if (item.getDefinition().getId().startsWith(MOD_ID)) {
                matchedItems.add(item);
            }
        }
        
        if (matchedItems.size() > 1) {
            MineTweakerAPI.logError("An ore dictionary <ore:" + oreDictEntry.getName() + "> has multiple items owned by GregTech");
        } else if (matchedItems.size() == 0) {
            MineTweakerAPI.logError("An ore dictionary <ore:" + oreDictEntry.getName() + "> has not items owned by GregTech");
            return null;
        }
        
        return matchedItems.get(0); //there can be only one
	}
}
