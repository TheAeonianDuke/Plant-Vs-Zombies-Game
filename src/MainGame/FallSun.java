package MainGame;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FallSun {
    private TranslateTransition movesun;
    private final int suncounter=50;
    private Text SunCounter;
    private boolean ismove=false;

    private final ImageView sun_img= new ImageView("main/resources/sun.gif");

    public FallSun(Text SunCounter, Double xPos) {
        sun_img.setLayoutX(xPos);
        sun_img.setLayoutY(-60);
        this.SunCounter=SunCounter;
    }

    public void FallSun() {
        movesun = new TranslateTransition(Duration.seconds(4), sun_img);
        movesun.setToY(sun_img.getLayoutY() + 760);
//        move.setCycleCount(10);
        movesun.play();
        sun_img.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sun_img.setVisible(false);
                SunCounter.setStyle("-fx-border-color: #ffcf00");
                SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) + suncounter));
            }
        });
    }

    public ImageView getSun_img() {
        return sun_img;
    }

    public boolean isIsmove() {
        return ismove;
    }

    public void setIsmove(boolean ismove) {
        this.ismove = ismove;
    }
}
