package data;

public enum Upgrade {
    MERCHANT_SCROLL("Merchant's Scroll", 50.00, Skill.MERCHANT, 2,
            "This scroll increases your Merchant skill by 2 points", "images/Scroll.jpg"),

    PILOT_SCROLL("Pilot's Scroll", 75.00, Skill.PILOT, 2,
            "This scroll increases your Pilot skill by 2 points", "images/Scroll.jpg"),

    FIGHTER_SCROLL("Fighter's Scroll", 75.00, Skill.PILOT, 2,
            "This scroll increases your Fighter skill by 2 points", "images/Scroll.jpg"),

    ENGINEER_SCROLL("Engineer's Scroll", 15.00, Skill.ENGINEER, 2,
            "This scroll increases your Engineer skill by 2 points", "images/Scroll.jpg");

    private String name;
    private double price;
    private Skill skill;
    private int buff;
    private String description;
    private String imagePath;
    
    Upgrade(String name, double price, Skill skill,
            int buff, String description, String imagePath) {
        this.name = name;
        this.price = price;
        this.skill = skill;
        this.buff = buff;
        this.description = description;
        this.imagePath = imagePath;
    }



    public String getName() {
        return this.name;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public int getBuff() {
        return this.buff;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public double getPrice() {
        return this.price;
    }
}
