class node
{
	node left, right;
	int data;

	// красное ==> true, черное ==> false
	boolean color;
	
	node(int data)
	{
		this.data = data;
		left = null;
		right = null;
		
		// Новая Node которая создается
		// всегда красного цвета
		color = true;
	}
}

public class HW4j {

	private static node root = null;
	
	// служебная функция для поворота узла против часовой стрелки.
	node rotateLeft(node myNode)
	{
		System.out.printf("left rotation!!\n");
		node child = myNode.right;
		node childLeft = child.left;

		child.left = myNode;
		myNode.right = childLeft;

		return child;
	}

	// служебная функция для поворота узла по часовой стрелке.
	node rotateRight(node myNode)
	{
		System.out.printf("right rotation\n");
		node child = myNode.left;
		node childRight = child.right;

		child.right = myNode;
		myNode.left = childRight;

		return child;
	}

	// utility function to check whether
	// node is red in color or not.
	boolean isRed(node myNode)
	{
		if (myNode == null)
			return false;
		return (myNode.color == true);
	}

	// служебная функция для проверки того, является ли
	// nodes.
	void swapColors(node node1, node node2)
	{
		boolean temp = node1.color;
		node1.color = node2.color;
		node2.color = temp;
	}

	// вставка в наклоненное влево Красно-черное дерево.
	node insert(node myNode, int data)
	{
		// Обычный код вставки для любого двоичного файла
		// Дерево поиска.
		if (myNode == null)
			return new node(data);

		if (data < myNode.data)
			myNode.left = insert(myNode.left, data);
		
		else if (data > myNode.data)
			myNode.right = insert(myNode.right, data);
		
		else
			return myNode;


		// case 1.
		// когда правый дочерний элемент красный, а левый дочерний элемент -
		// Черный или не существует.
		if (isRed(myNode.right) && !isRed(myNode.left))
		{
			// поверните узел влево, чтобы превратить его в
			// допустимая структура.
			myNode = rotateLeft(myNode);

			// поменяйте местами цвета в качестве дочернего узла
			// всегда должен быть красным
			swapColors(myNode, myNode.left);

		}

		// case 2
		// когда оставленный ребенок, а также оставленный внук окрашены в красный цвет
		if (isRed(myNode.left) && isRed(myNode.left.left))
		{
			// поверните текущий узел вправо, чтобы превратить
			// его в допустимую структуру.
			myNode = rotateRight(myNode);
			swapColors(myNode, myNode.right);
		}


		// case 3
		// когда и левый, и правый дочерние элементы окрашены в красный цвет.
		if (isRed(myNode.left) && isRed(myNode.right))
		{
			// так же измените цвет узла
			// это левый и правый ребенок.
			myNode.color = !myNode.color;

			// измените цвет на черный.
			myNode.left.color = false;
			myNode.right.color = false;
		}

		return myNode;
	}


	// Обход по порядку
	void inorder(node node)
	{
		if (node != null)
		{
			inorder(node.left);
			System.out.print(node.data + " ");
			inorder(node.right);
		}
	}

	public static void main(String[] args) {
	
	HW4j node = new HW4j();
	
	root = node.insert(root, 10);
	// чтобы убедиться, что корень остается черным, 
	// выберите цвет
	root.color = false;
	
	root = node.insert(root, 20);
	root.color = false;
	
	root = node.insert(root, 30);
	root.color = false;
	
	root = node.insert(root, 40);
	root.color = false;
	
	root = node.insert(root, 50);
	root.color = false;
	
	root = node.insert(root, 25);
	root.color = false;

	// отобразите дерево с помощью обхода по порядку.
	node.inorder(root);

	}
}
