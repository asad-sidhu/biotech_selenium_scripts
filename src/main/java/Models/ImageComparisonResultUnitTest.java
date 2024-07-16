//package Models;
//
//import com.github.romankh3.image.comparison.model.ImageComparisonResult;
//import com.github.romankh3.image.comparison.model.ImageComparisonState;
//
//import org.openqa.selenium.Rectangle;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.github.romankh3.image.comparison.ImageComparisonUtil.readImageFromResources;
//
//
//public class ImageComparisonResultUnitTest {
//
//    @Test
//    public void shouldProperlyWorkObjectCreation() {
//        //when
//        ImageComparisonResult imageComparisonResult = new ImageComparisonResult();
//
//        //then
//        Assert.assertNotNull(imageComparisonResult);
//    }
//
////    @DisplayName("Should properly work getters and setters")
////    @Test
////    public void shouldProperlyWorkGettersAndSetters() {
////        //when
////        List<Rectangle> rectangles = new ArrayList<>();
////        rectangles.add(Rectangle.createDefault());
////        ImageComparisonResult imageComparisonResult = new ImageComparisonResult()
////                .setImageComparisonState(ImageComparisonState.MATCH)
////                .setExpected(readImageFromResources("expected.png"))
////                .setActual(readImageFromResources("actual.png"))
////                .setResult(readImageFromResources("result.png"))
////                .setRectangles(rectangles);
////
////        //then
////        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
////        assertNotNull(imageComparisonResult.getExpected());
////        assertNotNull(imageComparisonResult.getActual());
////        assertNotNull(imageComparisonResult.getResult());
////        assertEquals(imageComparisonResult.getRectangles(), rectangles);
////    }
//
//}
