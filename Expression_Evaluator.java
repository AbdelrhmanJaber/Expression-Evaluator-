import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @param object to insert*
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    public int size();
}

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

class node{
    Object data;
    node next;
}

class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
      node head;

    public void add(int index, Object element) {
        if (index < 0)
            throw new NullPointerException();
        node temp = head;
        node link = new node();
        link.data = element;
        link.next = null;
        if (index == 0) {
            link.next = head;
            head = link;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            link.next = temp.next;
            temp.next = link;
        }
    }

    public void add(Object element) {
        node link = new node();
        link.data = element;
        link.next = null;
        if (head == null) {
            head = link;
        } else {
            node temp = head;
            while ((temp.next) != null) {
                temp = temp.next;
            }
            temp.next = link;
        }
    }


    public Object get(int index) {
        Object value;
        if (index < 0) {
            throw new NullPointerException();
        } else {
            node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            value = temp.data;
        }
        return value;
    }


    public void set(int index, Object element) {
        if (index < 0) {
            throw new NullPointerException();
        } else {
            node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.data = (int) element;
        }
    }


    public void clear() {
        if (size() == 0) {
            return;
        }
        head = null;
    }


    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }


    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new NullPointerException();
        }
        node temp = head;
        if (index == 0) {
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

    public int size() {
        int counter = 0;
        node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || fromIndex >= size() || toIndex < 0 || toIndex >= size())
            throw new NullPointerException();
        SingleLinkedList subList = new SingleLinkedList();
        node temp = head;
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.next;
        }
        head = temp; //to print the list from the desired position
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(temp.data);
            temp = temp.next;
        }
        temp.next = null;
        return subList;
    }

    public boolean contains(Object o) {
        node temp = head;
        int flag = 0;
        while (temp != null) {
            if (temp.data ==  o) {
                flag = 1;
            }
            temp = temp.next;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    public void print() {
        node temp = head;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(", ");
            temp = temp.next;
        }
        System.out.print("]");
    }
}
class MyStack implements IStack {
    SingleLinkedList list;
    MyStack() {
         this.list = new SingleLinkedList();
    }
    int size = 0;

    @Override
    public Object pop() {
        Object val = list.get(0);
        list.remove(0);
        size--;
        return val;
    }

    @Override
    public Object peek() {
        return list.get(0);
    }

    @Override
    public void push(Object element) {
        list.add(0, element);
        size++;
    }

    @Override
    public boolean isEmpty() {
        if (list.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return size;
    }

    public void printStack() {
        list.print();
    }
}
interface IExpressionEvaluator {

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression);


    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * @param expression postfix expression
     * @return the expression evaluated value
     */

    public int evaluate(String expression);

}


public class Main implements IExpressionEvaluator {

    public static int a;
    public static int b;
    public static int c;
    int precedent(char operand) {
        if (operand == '^')
            return 4;
        else if (operand == '*' || operand == '/')
            return 3;
        else if (operand == '+' || operand == '-')
            return 2;
        else if (operand == '(' || operand == ')')
            return 1;
        else
            return 0;
    }

    String CleanString(String expression) {
        if (expression.startsWith("--"))
            expression = expression.replaceFirst("--", "");
        expression = expression.replace("+--", "+");
        expression = expression.replace("*--", "*");
        expression = expression.replace("/--", "/");
        expression = expression.replace("^--", "^");
        if (expression.contains("--"))
            expression = expression.replaceAll("--", "+");
        return expression;
    }

   int CheckError(String expression){
        char []arr = expression.toCharArray();
        int index = expression.length()-1;
        if(arr[0] == '/' || arr[0] == '*' || arr[0] == '^' || arr[0] == ')') {
            return 0;
        }
       if(arr[index] == '/' || arr[index] == '*' || arr[index] == '^' || arr[index] == '(') {
           return 0;
       }
       if(arr[index] == '+' || arr[index] == '-'){
           return 0;
       }
        return 1;
    }
    @Override
    public String infixToPostfix(String expression) {
        MyStack s = new MyStack();
        s.list = new SingleLinkedList();
        expression = CleanString(expression);
        if(CheckError(expression) == 0){
            throw new RuntimeException();
        }
        String postfix = "";
        for (int i = 0; i < expression.length(); i++) {
            char operand = expression.charAt(i);
            if (Character.isLetter(operand))
                postfix += operand;
            else if (operand == '(') {
                s.push(operand);
            } else if (operand == ')') {
                while (!s.isEmpty() && (char) s.peek() != '(') {
                    postfix += s.pop();
                }
                s.pop();
            } else {
                while (!s.isEmpty() && operand != '(' && precedent((char) s.peek()) >= precedent(operand)) {
                    postfix += s.pop();
                }
                s.push(operand);
            }
        }
        while (!s.isEmpty() && (char) s.peek() != '(') {
            postfix += s.pop();
        }
        if (!s.isEmpty() && (char) s.peek() == '(')
            throw new NullPointerException();
        return postfix;
    }

    @Override
    public int evaluate(String expression) {
        MyStack s = new MyStack();
        s.list = new SingleLinkedList();
        int op2 , op1;
        int flag = 0;
        int result = 0;
        for (int i = 0 ; i < expression.length() ; i++){
            if(Character.isLetter(expression.charAt(i))){
                s.push(WhichChar(expression.charAt(i)));
            }
            else if (IsOperation(expression.charAt(i)) == 1){
                op2 = (int) s.pop();
                if(s.isEmpty() && expression.charAt(i) == '-'){
                    result = op2 * -1;
                    s.push(result);
                    flag = 1;
                }
                else{
                    op1 = (int) s.pop();
                    result = Operation(op1 , op2 , expression.charAt(i));
                    s.push(result);
                    flag = 1;
                }
            }
            else{
                throw new RuntimeException();
            }
        }
        if(flag == 0 && expression.length() == 1){
            result = (int)s.pop();
        }
        else if(flag == 0 && expression.length() > 1){
            throw new RuntimeException();
        }
        return result;
    }

    int Operation(int op1 , int op2 , char c){
        int result = 0;
        switch (c){
            case '+':{
                result = op1 + op2;
                break;
            }
            case '-':{
                result = op1 - op2;
                break;
            }
            case '*':{
                result = op1*op2;
                break;
            }
            case '/':{
                if(op2 == 0)
                    throw new RuntimeException();
                else
                   result = op1/op2;
                break;
            }
            case '^':{
                result = (int) Math.pow((double)op1 , (double) op2);
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        return result ;
    }
    int WhichChar (char temp){
        if(temp == 'a')
            return a;
        else if (temp == 'b')
            return b;
        else if (temp == 'c')
            return c;
        else
            throw new RuntimeException();
    }
    int IsOperation(char op){
        if(op == '+' || op == '-' || op == '*' || op == '/' || op == '^')
            return 1;
        else
            return 0;
    }
    private static void SetGlobalVariables(char num , int res){
        switch (num){
            case 'a':{
                a = res;
                break;
            }
            case 'b':{
                b = res;
                break;
            }
            case 'c':{
                c = res;
                break;
            }
            default:
                throw new RuntimeException();
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main eval = new Main();
        String expression = input.nextLine();
        int res, temp;
        for (int i = 0; i < 3; i++) {
            String num = input.nextLine();
            temp = 0;
            res = 0;
            if (num.charAt(2) == '-') {
                for (int j = 3; j < num.length(); j++) {
                    int scale = (int) Math.pow(10, num.length() - j - 1);
                    temp = (int) (num.charAt(j)) - (int) ('0');
                    temp = temp * scale;
                    res += temp;
                }
                res = res * -1;
            }
            else {
                for (int j = 2 ; j < num.length() ; j++) {
                    int scale = (int) Math.pow(10, num.length() - j - 1);
                    temp = (int) (num.charAt(j)) - (int) ('0');
                    temp = temp * scale;
                    res += temp;
                }
            }
            SetGlobalVariables(num.charAt(0) , res);
        }
        try {
            expression = eval.infixToPostfix(expression);
            int result = eval.evaluate(expression);
            System.out.println(expression);
            System.out.println(result);
        }
        catch (NullPointerException npe){
            System.out.print("Error");
        }
        catch (RuntimeException rte){
            System.out.print("Error");
        }
        input.close();
    }
}