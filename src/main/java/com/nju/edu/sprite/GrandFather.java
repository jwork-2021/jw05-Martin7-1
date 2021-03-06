package com.nju.edu.sprite;

import com.nju.edu.screen.GameScreen;
import com.nju.edu.skill.CDSkill;
import com.nju.edu.skill.MoveSkill;
import com.nju.edu.skill.RecoverSkill;
import com.nju.edu.skill.Skill;
import com.nju.edu.util.ReadImage;

import java.awt.image.BufferedImage;

/**
 * 爷爷类
 * @author Zyi
 */
public class GrandFather extends Sprite {

    private static final GrandFather GRAND_FATHER = new GrandFather(0, 320);

    public static GrandFather getInstance() {
        return GRAND_FATHER;
    }

    private GrandFather(int x, int y) {
        super(x, y, 100, 100, ReadImage.GrandFather);
    }

    /**
     * 给予技能的次数
     */
    private int giveTime = 0;
    private Calabash calabash = Calabash.getInstance();
    private int speed = calabash.getSpeed();

    public void moveUp() {
        if (this.y - speed >= 0) {
            this.y -= speed;
        }
    }

    public void moveDown() {
        if (this.y + speed <= GameScreen.getHei() - 150) {
            this.y += speed;
        }
    }

    public void moveLeft() {
        if (this.x - speed >= 0) {
            this.x -= speed;
        }
    }

    public void moveRight() {
        if (this.x + speed <= GameScreen.getWid() - 150) {
            this.x += speed;
        }
    }

    /**
     * 给予葫芦娃一个技能
     */
    public void giveSkill() {
        // 循环给予
        if (giveTime % Skill.SKILL_AMOUNT == 0) {
            System.out.println("give move skill");
            this.calabash.setSkill(new MoveSkill());
        } else if (giveTime % Skill.SKILL_AMOUNT == 1) {
            System.out.println("give cd skill");
            this.calabash.setSkill(new CDSkill());
        } else if (giveTime % Skill.SKILL_AMOUNT == 2) {
            System.out.println("give recover skill");
            this.calabash.setSkill(new RecoverSkill());
        }
        this.giveTime++;
    }

    public void speedUp(boolean isSpeedUp) {
        if (isSpeedUp) {
            this.speed += 5;
        }
    }

    public void clearSkillImpact() {
        if (calabash.haveSkill()) {
            if ("MoveSkill".equals(calabash.getCurSkill().getName()) && this.speed == 15) {
                this.speed -= 5;
            }
        }
    }
}
