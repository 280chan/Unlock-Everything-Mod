package mymod;

import java.util.ArrayList;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.CardCrawlGame.GameMode;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostUpdateSubscriber;

/**
 * @author 彼君不触
 * @version 6/1/2020
 * @since 8/26/2018
 */

@SpireInitializer
public class UnlockEverythingMod implements PostUpdateSubscriber, PostInitializeSubscriber {
	public static boolean unlocked = false;
	
	public static void initialize() {
		BaseMod.subscribe(new UnlockEverythingMod());
	}

	@Override
	public void receivePostInitialize() {
	}

	public static void unlockAll(){
		if (CardCrawlGame.mode != GameMode.CHAR_SELECT || unlocked)
			return;
		unlocked = true;
		unlockAscensionLevel(20);
		unlockFinalAct();
		unlockDaily();
		unlockRelics();
		unlockCards();
	}

	@Override
	public void receivePostUpdate() {
		unlockAll();
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
		for (Prefs p : prefs)
			if (p.getInteger("BOSS_KILL", 0) > 0)
				return;
		prefs.get(0).putInteger("BOSS_KILL", 1);
		prefs.get(0).flush();
	}
	
	public static void unlockCards() {
		CardLibrary.initialize();
		for (String id : CardLibrary.CARD_IDS)
			UnlockTracker.unlockCard(id);
	}
	
	public static void unlockRelics() {
		RelicLibrary.initialize();
		for (String id : RelicLibrary.RELIC_IDS) {
			UnlockTracker.unlockPref.putInteger(id, 2);
			if (com.megacrit.cardcrawl.helpers.RelicLibrary.getRelic(id) != null
					&& !com.megacrit.cardcrawl.helpers.RelicLibrary.getRelic(id).isSeen) {
				com.megacrit.cardcrawl.helpers.RelicLibrary.getRelic(id).isSeen = true;
				UnlockTracker.relicSeenPref.putInteger(id, 1);
			}
		}
		UnlockTracker.unlockPref.flush();
		UnlockTracker.relicSeenPref.flush();
		UnlockTracker.lockedRelics.clear();
	}
	
	public static void unlockAscensionLevel(int level) {
		UnlockTracker.hardUnlockOverride("The Silent");
		UnlockTracker.hardUnlockOverride("Defect");
		UnlockTracker.hardUnlockOverride("Watcher");
		ArrayList<Prefs> prefs = new ArrayList<Prefs>();
		prefs.add(SaveHelper.getPrefs("STSDataVagabond"));
		prefs.add(SaveHelper.getPrefs("STSDataTheSilent"));
		prefs.add(SaveHelper.getPrefs("STSDataDefect"));
		prefs.add(SaveHelper.getPrefs("STSDataWatcher"));
		
		for (Prefs pref : prefs) {
			if (pref.getInteger("WIN_COUNT", 0) == 0)
				pref.putInteger("WIN_COUNT", 1);
			if (level < 1)
				level = 1;
			else if (level > 20) {
				level = 20;
			}
			pref.putInteger("ASCENSION_LEVEL", level);
	        pref.putInteger("LAST_ASCENSION_LEVEL", level);
			BaseMod.logger.info("ASCENSION LEVEL IS NOW: " + level);
			pref.flush();
		}
	}

}
