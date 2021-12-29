<?php
interface Shape {
    // Có thể định nghĩa sẵn const hoặc không
    const SQUARE = 1;
    const CIRCLE = 2;
    const RECTANGLE = 3;
    // general method
    function draw();
}

class Circle implements Shape
{
    public function draw() {
        // thực hiện code vẽ hình tròn tại đây
        echo "Draw circle";
    }
}

class Square implements Shape
{
    public function draw() {
        // thực hiện code vẽ hình vuông tại đây
        echo "Draw square";
    }
}

//Abtract 2

interface Color
{
    // general method
    function fill();
}


class Red implements Color
{
    public function fill()
    {
        echo "Filled red";
    }
}

class Green implements Color
{
    public function fill() {
        echo "Filled green";
    }
}

// Abtract factory
abstract class AbstractFactory {
    abstract function getColor($color);
    abstract function getShape($shape) ;
}

// Factory method
class ShapeFactory extends AbstractFactory
{
    public function getShape($type) {
        switch ($type) {
            case Shape::SQUARE:
                return new Square;
                break;
            case Shape::CIRCLE:
                return new Circle;
                break;
            case Shape::RECTANGLE:
                return new Rectangle;
                break;
            default:
                return nullbreak;
        }
        return null;
    }

    public function getColor($type) {

    }
}

class ColorFactory extends AbstractFactory
{
    public function getColor($color) {
        switch (strtolower($color)) {
            case 'red':
                return new Red();
                break;
            case 'blue':
                return new Blue()
                break;
            case 'green':
                return new Green()
                break;
            default:
                return null
                break;
        }
        return null;
    }
}

// Tạo FactoryProducer để khởi tạo 1 abstract Shape/Color Factory
class FactoryProducer {
    public static function getFactory($choice)
    {
        $choice = strtolower($choice);
        if($choice == 'shape'){
            return new ShapeFactory;
        } elseif($choice == 'color'){
            return new ColorFactory;
        }
        return null;
    }
}

// In ra
// include các class cần sử dụng
include ...
// khởi tạo lớp ShapeFactory (tạo ra "nhà máy")
$shapeFactory = FactoryProducer::getFactory('shape');
// lấy object Shape với kiểu hình là Circle (lấy "sản phẩm")
$shape = $shapeFactory->getShape(Shape::CIRCLE);
//gọi method draw từ Shape Circle  (sử dụng)
$shape->draw();
//Tương tự với màu sắc
$colorFactory = FactoryProducer::getFactory('color');
$color= $shapeFactory->getColor('red');
$color->fill();