package konsola5.overleveled.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @WrapOperation(
            method = "createResult",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I",
            ordinal = 1)
    )
    private int overleveled$allowOverleveledBooks(Enchantment instance,
                                                  Operation<Integer> original,
                                                  @Local(ordinal = 2) LocalRef<ItemStack> secondItem,
                                                  @Local(ordinal = 0) LocalRef<Enchantment> enchantment,
                                                  @Local(ordinal = 0) LocalRef<Map<Enchantment, Integer>> firstItemEnchantmentMap,
                                                  @Local(ordinal = 1) LocalRef<Map<Enchantment, Integer>> secondItemEnchantmentMap) {
        int firstItemLevel = firstItemEnchantmentMap.get().getOrDefault(enchantment.get(), 0);
        int secondItemLevel = secondItemEnchantmentMap.get().get(enchantment.get());
        if (secondItem.get().is(Items.ENCHANTED_BOOK)) {
            return Math.max(firstItemLevel, secondItemLevel);
        } else {
            return original.call(instance);
        }
    }
}
