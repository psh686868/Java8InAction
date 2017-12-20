package lambdasinaction.chap2.predicatepsh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApplesPSH {

	public static void main(String ... args){

		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));	

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		/*List<Apple> greenApples = filterApplesByColor(inventory, "green");
		System.out.println(greenApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, "red");
		System.out.println(redApples);*/


		/***********************start这样就可以把filterApples方法迭代集合逻辑和应用集合中每个元素的行为（这里指一个谓词）区分开了，但是file只接受实现了ApplePredicate
		 * **********************的接口的类，所以必须把代码包裹在ApplePredicate类里面 ，然后再把行为传递过去，如果使用λ可以直接将行为即方法传递过去，会在第三章里讲到的**/
		//[Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filter(inventory,new AppleColorPredicate());
		//System.out.println(greenApples);

		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		//System.out.println(heavyApples);
		/********************************************************** end **************************************************************************/


		/**********************************虽然上面使用了好的方法，但是代码量也很多，比如需求一变写实现接口的类 使用匿名类来简化代码*/
		List<Apple> redApples = filter(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return apple.getColor().equals("red");
			}
		});
		//System.out.println(redApples);
		/*************************匿名类传递行为函数end************************8*/


		/**************************将上面的方法进一步封装 既然实现了行为的策略模式的封装，进一步将集合进行封装 实现模板化这样的集合不止能传苹果，香蕉什么的也可以了*/
		List<Apple> redApplesGen = filterGener(inventory, new PredicateGener<Apple>() {
			@Override
			public boolean test(Apple apple) {
				return apple.getColor().equals("red");
			}
		});
		System.out.println(redApplesGen);


		// [Apple{color='red', weight=120}]
		/*List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple a){
				return a.getColor().equals("red"); 
			}
		});
		System.out.println(redApples2);*/

	}

	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if("green".equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}


	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}

	/*****************************使用策略模式 根据传入不同的实现接口对象，执行不能的实现接口的方法， 也相当于传入函数的形式 也相当于模板************************************/
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple>  result = new ArrayList();
		if(inventory!=null&&inventory.size()>0){
			for (Apple apple:inventory) {
				if(p.test(apple)){
					result.add(apple);
				}
			}
			return result;
		}
		return null;
	}

	/*****************************改造上面的方法，上面是对谓词的参数化，这次对集合进行模板话************************************/
	public static <T> List<T> filterGener(List<T> inventory, PredicateGener p){
		List<T>  result = new ArrayList();
		if(inventory!=null&&inventory.size()>0){
			for (T t:inventory) {
				if(p.test(t)){
					result.add(t);
				}
			}
			return result;
		}
		return null;
	}



	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}


}