package konsola5.overleveled;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Overleveled implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("overleveled");

	@Override
	public void onInitialize() {
		LOGGER.info("Apply overleveled enchantments from books with your anvil!");
	}
}