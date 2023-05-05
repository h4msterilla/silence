package game.vt.silence.vaadin.silencemenu.charactermenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.model.values.VTValueTag;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.*;

public class CharacterMenu extends VerticalLayout {

    private VTCharacter selectedCharacter = null;
    private VTCharacterValueService vtCharacterValueService;
    private List<VTCharacterValue> vtCharacterValueList;


    private Grid<VTCharacterValue> gridBase = new Grid<>(VTCharacterValue.class, false);

    private Grid<VTCharacterValue> gridHealth = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridSanity = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridEndurance = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridSwiftness = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridMind = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridEgo = new Grid<>(VTCharacterValue.class, false);

    private Grid<VTCharacterValue> gridConnections = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridKnowledge = new Grid<>(VTCharacterValue.class, false);

    private Grid<VTCharacterValue> gridCaPDefault = new Grid<>(VTCharacterValue.class, false);
    private Grid<VTCharacterValue> gridCaPCustom = new Grid<>(VTCharacterValue.class, false);

    private List<Grid<VTCharacterValue>> gridList = new ArrayList<>(){{
        add(gridBase);
        add(gridHealth);
        add(gridSanity);
        add(gridEndurance);
        add(gridSwiftness);
        add(gridMind);
        add(gridEgo);
        add(gridConnections);
        add(gridKnowledge);
        add(gridCaPDefault);
        add(gridCaPCustom);
    }};

    private VerticalLayout ticksAndExpAndTalentLimitLayoutMenu = new VerticalLayout();
    private VerticalLayout statsLayoutMenu = new VerticalLayout();
    private VerticalLayout cakLayoutMenu = new VerticalLayout();
    private VerticalLayout capLayoutMenu = new VerticalLayout();
    private VerticalLayout talentLayoutMenu = new VerticalLayout();

    private Tab baseTab = new Tab("Base");
    private Tab statsTab = new Tab("Stats");
    private Tab cakTab = new Tab("Connection and Knowledge");
    private Tab capTab = new Tab("Conditions and Processes");
    private Tab talentsTab = new Tab("Talents");
    private Tabs characterTabs = new Tabs(baseTab, statsTab, cakTab, capTab, talentsTab);

    private Map<Tab, VerticalLayout> menuMap= new HashMap<>(){{
        put(baseTab, ticksAndExpAndTalentLimitLayoutMenu);
        put(statsTab, statsLayoutMenu);
        put(cakTab, cakLayoutMenu);
        put(capTab, capLayoutMenu);
        put(talentsTab, talentLayoutMenu);
    }};

    public CharacterMenu() {
        setSizeFull();

        vtCharacterValueService = SpringContextProvider.getVtCharacterValueService();

        characterTabs.addSelectedChangeListener(e -> {
            Tab selectedTab = e.getSelectedTab();
            menuMap.forEach( (t,l) -> l.setVisible(false));
            menuMap.get(selectedTab).setVisible(true);
        });
        add(characterTabs);

        HorizontalLayout layoutBase = new HorizontalLayout(gridBase);
        gridBase.getStyle().set("height", "145px");
        ticksAndExpAndTalentLimitLayoutMenu.add(layoutBase);

        HorizontalLayout layoutVital = new HorizontalLayout(gridHealth, gridSanity);
        gridHealth.getStyle().set("height", "145px");
        gridSanity.getStyle().set("height", "145px");
        HorizontalLayout layoutPhysic = new HorizontalLayout(gridEndurance, gridSwiftness);
        gridEndurance.getStyle().set("height", "253px");
        gridSwiftness.getStyle().set("height", "253px");
        HorizontalLayout layoutMind = new HorizontalLayout(gridMind, gridEgo);
        gridMind.getStyle().set("height", "253px");
        gridEgo.getStyle().set("height", "253px");
        statsLayoutMenu.add(layoutVital, layoutPhysic, layoutMind);

        HorizontalLayout layoutCak = new HorizontalLayout(gridConnections, gridKnowledge);
        gridConnections.getStyle().set("height","361px");
        gridKnowledge.getStyle().set("height","361px");
        cakLayoutMenu.add(layoutCak);

        HorizontalLayout layoutCap = new HorizontalLayout(gridCaPDefault, gridCaPCustom);
        gridCaPDefault.getStyle().set("height", "433px");
        gridCaPCustom.getStyle().set("height", "433px");
        capLayoutMenu.add(layoutCap);

        talentLayoutMenu.add(new Label("talent"));

        menuMap.forEach((t,m) -> {
            add(m);
            m.getChildren()
                    .filter(component -> component instanceof HorizontalLayout)
                    .forEach(hl -> {
                        ((HorizontalLayout) hl).setAlignItems(Alignment.CENTER);
                        ((HorizontalLayout) hl).setWidth("95%");
                    });
        });

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        gridList.forEach(g -> {
            g.setWidth("45%");
            g.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

            g.getStyle().set("font-size", "0.8rem");

            g.addColumn(VTCharacterValue::getValueName).setWidth("30%");
            g.addColumn(VTCharacterValue::getValue).setWidth("5%");
            g.addComponentColumn(v -> {
                CharacterModifyButtonsLayout buttonsLayout = new CharacterModifyButtonsLayout(selectedCharacter, vtCharacterValueList, v, gridList);
                buttonsLayout.setMargin(false);
                return buttonsLayout;
            }).setWidth("15%");
        });

    }

    public void setSelectedCharacter(VTCharacter vtCharacter) {

        menuMap.forEach((t,m) -> m.setVisible(false));

        if (vtCharacter == null) {
            Notification.show("No character selected!");
        } else {
            selectedCharacter = vtCharacter;
            Notification.show("selected character: " + vtCharacter.getCharname());

            vtCharacterValueList = vtCharacterValueService.getVTCharacterValuesByVTCharacter(selectedCharacter);

            setupGrids();

            characterTabs.setSelectedTab(baseTab);
            menuMap.get(baseTab).setVisible(true);
        }
    }

    private void setupGrids() {
        vtCharacterValueList.sort((v1, v2) -> {
            return v1.getValueName().compareTo(v2.getValueName());
        });

        gridBase.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.BASE)));

        gridHealth.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.HEALTH)));
        gridSanity.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.SANITY)));
        gridEndurance.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.BLOCK_ENDURANCE)));
        gridSwiftness.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.BLOCK_SWIFTNESS)));
        gridMind.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.BLOCK_MIND)));
        gridEgo.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.BLOCK_EGO)));

        gridConnections.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.CONNECTIONS)));
        gridKnowledge.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.KNOWLEDGE)));

        gridCaPDefault.setItems(vtCharacterValueList.stream().filter(v -> v.containsTag(VTValueTag.CONDITIONS_AND_PROCESSES)));
    }
}
