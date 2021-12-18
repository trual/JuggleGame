package trual.com.juggleGame.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrickDto {
    private int id;
    private int balls;
    private String name;
    private String animation;
    private List<List<Integer>> prereq;

}
