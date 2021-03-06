package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RepairItemRecipe extends SpecialRecipe {
   public RepairItemRecipe(ResourceLocation p_i51524_1_) {
      super(p_i51524_1_);
   }

   public boolean matches(CraftingInventory p_77569_1_, World p_77569_2_) {
      List<ItemStack> list = Lists.newArrayList();

      for(int i = 0; i < p_77569_1_.getContainerSize(); ++i) {
         ItemStack itemstack = p_77569_1_.getItem(i);
         if (!itemstack.isEmpty()) {
            list.add(itemstack);
            if (list.size() > 1) {
               ItemStack itemstack1 = list.get(0);
               if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getCount() != 1 || itemstack.getCount() != 1 || !itemstack1.getItem().canBeDepleted()) {
                  return false;
               }
            }
         }
      }

      return list.size() == 2;
   }

   public ItemStack assemble(CraftingInventory p_77572_1_) {
      List<ItemStack> list = Lists.newArrayList();

      for(int i = 0; i < p_77572_1_.getContainerSize(); ++i) {
         ItemStack itemstack = p_77572_1_.getItem(i);
         if (!itemstack.isEmpty()) {
            list.add(itemstack);
            if (list.size() > 1) {
               ItemStack itemstack1 = list.get(0);
               if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getCount() != 1 || itemstack.getCount() != 1 || !itemstack1.getItem().canBeDepleted()) {
                  return ItemStack.EMPTY;
               }
            }
         }
      }

      if (list.size() == 2) {
         ItemStack itemstack3 = list.get(0);
         ItemStack itemstack4 = list.get(1);
         if (itemstack3.getItem() == itemstack4.getItem() && itemstack3.getCount() == 1 && itemstack4.getCount() == 1 && itemstack3.getItem().canBeDepleted()) {
            Item item = itemstack3.getItem();
            int j = item.getMaxDamage() - itemstack3.getDamageValue();
            int k = item.getMaxDamage() - itemstack4.getDamageValue();
            int l = j + k + item.getMaxDamage() * 5 / 100;
            int i1 = item.getMaxDamage() - l;
            if (i1 < 0) {
               i1 = 0;
            }

            ItemStack itemstack2 = new ItemStack(itemstack3.getItem());
            itemstack2.setDamageValue(i1);
            Map<Enchantment, Integer> map = Maps.newHashMap();
            Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(itemstack3);
            Map<Enchantment, Integer> map2 = EnchantmentHelper.getEnchantments(itemstack4);
            Registry.ENCHANTMENT.stream().filter(Enchantment::isCurse).forEach((p_234828_3_) -> {
               int j1 = Math.max(map1.getOrDefault(p_234828_3_, 0), map2.getOrDefault(p_234828_3_, 0));
               if (j1 > 0) {
                  map.put(p_234828_3_, j1);
               }

            });
            if (!map.isEmpty()) {
               EnchantmentHelper.setEnchantments(map, itemstack2);
            }

            return itemstack2;
         }
      }

      return ItemStack.EMPTY;
   }

   @OnlyIn(Dist.CLIENT)
   public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
      return p_194133_1_ * p_194133_2_ >= 2;
   }

   public IRecipeSerializer<?> getSerializer() {
      return IRecipeSerializer.REPAIR_ITEM;
   }
}