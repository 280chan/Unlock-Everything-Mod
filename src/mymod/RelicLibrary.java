package mymod;

import java.util.ArrayList;

import com.megacrit.cardcrawl.relics.*;

public class RelicLibrary {
	static final ArrayList<String> RELIC_IDS = new ArrayList<String>();
	
	private static void add(AbstractRelic r) {
		RELIC_IDS.add(r.relicId);
	}
	
	private static void addAll(ArrayList<AbstractRelic> list) {
		for (AbstractRelic r : list)
			add(r);
	}
	
	static void initialize() {
		add(new Abacus());
	    add(new Akabeko());
	    add(new Anchor());
	    add(new AncientTeaSet());
	    add(new ArtOfWar());
	    add(new Astrolabe());
	    add(new BagOfMarbles());
	    add(new BagOfPreparation());
	    add(new BirdFacedUrn());
	    add(new BlackStar());
	    add(new BloodVial());
	    add(new BloodyIdol());
	    add(new BlueCandle());
	    add(new Boot());
	    add(new BottledFlame());
	    add(new BottledLightning());
	    add(new BottledTornado());
	    add(new BronzeScales());
	    add(new BustedCrown());
	    add(new Calipers());
	    add(new CallingBell());
	    add(new CaptainsWheel());
	    add(new Cauldron());
	    add(new CentennialPuzzle());
	    add(new CeramicFish());
	    add(new ChemicalX());
	    add(new ClockworkSouvenir());
	    add(new CoffeeDripper());
	    add(new Courier());
	    add(new CultistMask());
	    add(new CursedKey());
	    add(new DarkstonePeriapt());
	    add(new DeadBranch());
	    add(new DollysMirror());
	    add(new DreamCatcher());
	    add(new DuVuDoll());
	    add(new Ectoplasm());
	    add(new EmptyCage());
	    add(new Enchiridion());
	    add(new EternalFeather());
	    add(new FaceOfCleric());
	    add(new FossilizedHelix());
	    add(new FrozenEgg2());
	    add(new FrozenEye());
	    add(new FusionHammer());
	    add(new GamblingChip());
	    add(new Ginger());
	    add(new Girya());
	    add(new GoldenIdol());
	    add(new GremlinHorn());
	    add(new GremlinMask());
	    add(new HandDrill());
	    add(new HappyFlower());
	    add(new HornCleat());
	    add(new IceCream());
	    add(new IncenseBurner());
	    add(new InkBottle());
	    add(new JuzuBracelet());
	    add(new Kunai());
	    add(new Lantern());
	    add(new LetterOpener());
	    add(new LizardTail());
	    add(new Mango());
	    add(new MarkOfTheBloom());
	    add(new Matryoshka());
	    add(new MawBank());
	    add(new MealTicket());
	    add(new MeatOnTheBone());
	    add(new MedicalKit());
	    add(new MembershipCard());
	    add(new MercuryHourglass());
	    add(new MoltenEgg2());
	    add(new MummifiedHand());
	    add(new MutagenicStrength());
	    add(new Necronomicon());
	    add(new NeowsLament());
	    add(new NilrysCodex());
	    add(new NlothsGift());
	    add(new NlothsMask());
	    add(new Nunchaku());
	    add(new OddlySmoothStone());
	    add(new OddMushroom());
	    add(new OldCoin());
	    add(new Omamori());
	    add(new OrangePellets());
	    add(new Orichalcum());
	    add(new OrnamentalFan());
	    add(new Orrery());
	    add(new PandorasBox());
	    add(new Pantograph());
	    add(new PeacePipe());
	    add(new Pear());
	    add(new PenNib());
	    add(new PhilosopherStone());
	    add(new Pocketwatch());
	    add(new PotionBelt());
	    add(new PrayerWheel());
	    add(new PreservedInsect());
	    add(new PrismaticShard());
	    add(new QuestionCard());
	    add(new RedMask());
	    add(new RegalPillow());
	    add(new RunicDome());
	    add(new RunicPyramid());
	    add(new SacredBark());
	    add(new Shovel());
	    add(new Shuriken());
	    add(new SingingBowl());
	    add(new SlaversCollar());
	    add(new Sling());
	    add(new SmilingMask());
	    add(new SneckoEye());
	    add(new Sozu());
	    add(new SpiritPoop());
	    add(new SsserpentHead());
	    add(new StoneCalendar());
	    add(new StrangeSpoon());
	    add(new Strawberry());
	    add(new StrikeDummy());
	    add(new Sundial());
	    add(new ThreadAndNeedle());
	    add(new TinyChest());
	    add(new TinyHouse());
	    add(new Toolbox());
	    add(new Torii());
	    add(new ToxicEgg2());
	    add(new ToyOrnithopter());
	    add(new TungstenRod());
	    add(new Turnip());
	    add(new UnceasingTop());
	    add(new Vajra());
	    add(new VelvetChoker());
	    add(new Waffle());
	    add(new WarPaint());
	    add(new WarpedTongs());
	    add(new Whetstone());
	    add(new WhiteBeast());
	    add(new WingBoots());
	    
	    addAll(com.megacrit.cardcrawl.helpers.RelicLibrary.greenList);
	    addAll(com.megacrit.cardcrawl.helpers.RelicLibrary.blueList);
	    addAll(com.megacrit.cardcrawl.helpers.RelicLibrary.redList);
	    addAll(com.megacrit.cardcrawl.helpers.RelicLibrary.whiteList);
	}
}
