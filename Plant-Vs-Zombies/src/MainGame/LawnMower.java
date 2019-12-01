package MainGame;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LawnMower {
    public TranslateTransition movelawnmower;
    private boolean isTriggered=false;
    private ImageView img;

    public LawnMower(ImageView lawnmower) {
        img=lawnmower;
    }

    // LawnMower Anim //
    public void triggerLawnMower() {
        movelawnmower = new TranslateTransition(Duration.seconds(3), img);
        movelawnmower.setToX(img.getLayoutX() + 1500);
        movelawnmower.setCycleCount(1);
        movelawnmower.play();
        isTriggered=true;
    }

    public ImageView getImg() {
        return img;
    }

    public TranslateTransition getMovelawnmower() {
        return movelawnmower;
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean triggered) {
        isTriggered = triggered;
    }
}
