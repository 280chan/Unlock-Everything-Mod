package mymod;

import java.util.ArrayList;
import java.util.stream.Stream;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.SaveSlotScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;

/**
 * @author 彼君不触
 * @version 9/23/2022
 * @since 8/26/2018
 */

@SpireInitializer
public class UnlockEverythingMod implements PostInitializeSubscriber {
	
	public static void initialize() {
		BaseMod.subscribe(new UnlockEverythingMod());
	}

	@Override
	public void receivePostInitialize() {
		unlockAscensionLevel();
		unlockFinalAct();
		unlockDaily();
		unlockBetaArtAndEnding();
		unlockRelics();
		unlockCards();
	}
	
	public static void unlockBetaArtAndEnding() {
		String key = "THE_ENDING";
		if (!UnlockTracker.achievementPref.getBoolean(key, false)) {
			UnlockTracker.achievementPref.putBoolean(key, true);
			UnlockTracker.achievementPref.flush();
		}
	}
	
	public static void unlockFinalAct() {
		CardCrawlGame.playerPref.putBoolean(PlayerClass.IRONCLAD.name() + "_WIN", true);
		CardCrawlGame.playerPref.putBoolean(PlayerClass.THE_SILENT.name() + "_WIN", true);
		CardCrawlGame.playerPref.putBoolean(PlayerClass.DEFECT.name() + "_WIN", true);
		CardCrawlGame.playerPref.flush();
	}
	
	public static void unlockDaily() {
		ArrayList<Prefs> prefs = new ArrayList<Prefs>();
		prefs.add(SaveHelper.getPrefs("STSDataVagabond"));
		prefs.add(SaveHelper.getPrefs("STSDataTheSilent"));
		prefs.add(SaveHelper.getPrefs("STSDataDefect"));
		prefs.add(SaveHelper.getPrefs("STSDataWatcher"));
		if (prefs.stream().noneMatch(p -> p.getInteger("BOSS_KILL", 0) > 0)) {
			prefs.get(0).putInteger("BOSS_KILL", 1);
			prefs.get(0).flush();
		}
	}
	
	public static void unlockCards() {
		CardLibrary.initialize();
		for (String id : CardLibrary.CARD_IDS) {
			UnlockTracker.unlockPref.putInteger(id, 2);
			UnlockTracker.lockedCards.remove(id);
			AbstractCard c = com.megacrit.cardcrawl.helpers.CardLibrary.getCard(id);
			if (c != null && !c.isSeen) {
				c.isSeen = true;
				c.unlock();
				UnlockTracker.seenPref.putInteger(id, 1);
			}
		}
		UnlockTracker.seenPref.flush();
		UnlockTracker.unlockPref.flush();
	}
	
	public static void unlockRelics() {
		RelicLibrary.initialize();
		for (String id : RelicLibrary.RELIC_IDS) {
			UnlockTracker.unlockPref.putInteger(id, 2);
			UnlockTracker.lockedRelics.remove(id);
			AbstractRelic r = com.megacrit.cardcrawl.helpers.RelicLibrary.getRelic(id);
			if (r != null && !r.isSeen) {
				r.isSeen = true;
				UnlockTracker.relicSeenPref.putInteger(id, 1);
			}
		}
		UnlockTracker.unlockPref.flush();
		UnlockTracker.relicSeenPref.flush();
	}
	
	public static void unlockAscensionLevel() {
		UnlockTracker.hardUnlockOverride("The Silent");
		UnlockTracker.hardUnlockOverride("Defect");
		UnlockTracker.hardUnlockOverride("Watcher");
		CardCrawlGame.characterManager.getAllPrefs().stream().limit(4).forEach(pref -> {
			if (pref.getInteger("WIN_COUNT", 0) == 0)
				pref.putInteger("WIN_COUNT", 1);
			pref.putInteger("ASCENSION_LEVEL", 20);
	        pref.putInteger("LAST_ASCENSION_LEVEL", 20);
			pref.flush();
		});
		Stream.of("CROW", "DONUT", "WIZARD").forEach(UnlockTracker::markBossAsSeen);
	}
	
	@SpirePatch(clz = SaveSlotScreen.class, method = "open")
	public static class StupidInitPatch {
		public static void Postfix(SaveSlotScreen __instance, String curName) {
			if ("".equals(curName)) {
				Prefs p = SaveHelper.getPrefs("STSDataVagabond");
				if (p.getInteger("WIN_COUNT", 0) == 0) {
					p.putInteger("WIN_COUNT", 1);
					p.putInteger("ASCENSION_LEVEL", 20);
			        p.putInteger("LAST_ASCENSION_LEVEL", 20);
					p.flush();
				}
			}
		}
	}

}
