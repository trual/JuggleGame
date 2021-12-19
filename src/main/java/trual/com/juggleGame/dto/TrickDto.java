package trual.com.juggleGame.dto;

import lombok.Data;
import trual.com.juggleGame.model.Trick;

import java.util.Set;

@Data
public class TrickDto {
    private int id;
    private int balls;
    private String name;
    private String animation;
    private Set<Integer> prereq;

    public TrickDto() {
    }

    public TrickDto(int id, int balls, String name, String animation, Set<Integer> prereq) {
        this.id = id;
        this.balls = balls;
        this.name = name;
        this.animation = animation;
        this.prereq = prereq;
    }

    public TrickDto(Trick trick) {
        this.id = trick.getId();
        this.balls = trick.getBalls();
        this.name = trick.getName();
        this.animation = trick.getAnimation();
        this.prereq = trick.getPrereq();
    }
}
