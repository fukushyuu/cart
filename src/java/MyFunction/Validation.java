/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyFunction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nhat Nam
 */
public class Validation {
  public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

  public static int inputInteger(String message) {
    int number;
    while (true) {
      try {
        System.out.print(message);
        number = Integer.parseInt(in.readLine());
        break;
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return number;
  }

  public static double inputDouble(String message) {
    double number;
    while (true) {
      try {
        System.out.print(message);
        number = Double.parseDouble(in.readLine());
        break;
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return number;
  }

  public static String inputString(String message) {
    String str;
    while (true) {
      try {
        System.out.print(message);
        str = in.readLine();
        break;
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return str;
  }

  public static String inputDate(String message) {

    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    f.setLenient(false);
    Date date = new Date();
    while (true) {
      try {
        System.out.print(message);
        date = f.parse(in.readLine());
        break;
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }

    return f.format(date);
  }

  public static String inputName(String message) {
    String name;
    while (true) {
      try {
        System.out.print(message);
        name = in.readLine().trim();
        if (!name.matches("^[a-zA-Z\\s]+$")) {
          throw new Exception();
        }

        name = removeWhitespace(name.toLowerCase());
        String[] arrChar = name.split("");
        name = "";
        arrChar[0] = arrChar[0].toUpperCase();
        for (int j = 0; j < arrChar.length; j++) {
          if (arrChar[j].equals(" ")) {
            arrChar[j + 1] = arrChar[j + 1].toUpperCase();
          }
          name = name.concat(arrChar[j]);
        }
        break;
      } catch (Exception e) {
        System.err.println("Name cannot be left blank or contain special characters or numbers!");
      }
    }
    return name;
  }

  public static String removeWhitespace(String str) {
    while (str.contains("  ") || str.contains("\t")) {
      str = str.replaceAll("  ", " ");
      str = str.replaceAll("\t", " ");
    }
    return str;
  }

  static double inputPrice(String message) {
    double price;

    while (true) {
      try {
        System.out.print(message);
        price = Double.parseDouble(in.readLine());
        if (price > 0) {
          break;
        }
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return price;
  }

  static int inputQuantity(String message) {
    int quantity;

    while (true) {
      try {
        System.out.print(message);
        quantity = Integer.parseInt(in.readLine());
        if (quantity > 0) {
break;
        }
      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return quantity;
  }

  public static int inputIntegerLimit(String message, int min, int max) {
    int number;
    while (true) {
      try {
        System.out.print(message + " in range [" + min + ", " + max + "]: ");
        number = Integer.parseInt(in.readLine());
        if (number < min || number > max) {
          throw new NumberFormatException();
        }
        break;
      } catch (NumberFormatException e) {
        System.err.println("Please input number in range [" + min + ", " + max + "]");

      } catch (Exception e) {
        System.err.println("Wrong input!");
      }
    }
    return number;
  }

  static boolean inputYN(String message) {
    while (true) {
      try {
        System.out.print(message);
        String yn = in.readLine();
        if (yn.equalsIgnoreCase("Y")) {
          return true;
        }
        if (yn.equalsIgnoreCase("N")) {
          return false;
        }
        System.err.println("Input Yes (Y) or No (N)!");
      } catch (Exception e) {
      }
    }
  }
  public boolean checkLeng(int num){
    return false;
  }
}
