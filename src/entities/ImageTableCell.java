package entities;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell<S> extends TableCell<S, String> {
    private final ImageView imageView = new ImageView();

    @Override
    protected void updateItem(String image, boolean empty) {
        super.updateItem(image, empty);
        if (empty || image == null) {
            setGraphic(null);
        } else {
            Image imagee = new Image(getClass().getResourceAsStream("../image/"+image));
            imageView.setImage(imagee);
            setGraphic(imageView);
        }
    }
}