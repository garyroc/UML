import java.awt.*;

public class DrawController {
    private PAINT_TOOL currentState = PAINT_TOOL.THE_CLASSOBJECT;


    public enum PAINT_TOOL {
        SELECET, ASSOCIATION_LINE, GENERALIZATION_LINE, COMPOSITION_LINE, THE_CLASSOBJECT, THE_USECASE
    }

    public DrawController() {

    }

    public Drawable createDrawOject(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:
                break;
            case ASSOCIATION_LINE:
                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint);
                break;
            case GENERALIZATION_LINE:
                break;
            case COMPOSITION_LINE:
                break;
            case THE_CLASSOBJECT:
                resultDrawObject = new DrawableClass(givenStartPoint);
                break;
            case THE_USECASE:
                resultDrawObject = new DrawableUseCase(givenStartPoint);
                break;
        }
        return resultDrawObject;
    }

    public void chagePaintTool(PAINT_TOOL givenPaintTool) {

    }



}
