package ral;

import javax.microedition.lcdui.*;

public class SetupCanvas
    extends Canvas
    implements CommandListener
{
  private Font menuFont;
  private Font boldMenuFont;

  private Command yes;
  private Command no;
  private Command ok;
  private Command left;
  private Command right;

  private final Calc midlet;

  private String setupHeading;
  private String setupText;
  private String commandQueryHeading = "Setup: keys";
  private String commandQueryText =
    "Press \"yes\" if you see \"no\" and \"yes\" mapped to the left and right keys below";
  private String clearQueryHeading = "Setup: clear key";
  private String clearQueryText =
    "If you have a \"clear\" key, press it now, otherwise use #";

  private String bgrQueryHeading = "Setup: font";
  private String bgrQueryText = "What looks best, left or right numbers?";
  
  private String alertString;
  private String alertHeading;

  private static final int COMMAND_QUERY = 0;
  private static final int CLEAR_QUERY = 1;
  private static final int BGR_QUERY = 2;
  private static final int QUERY_FINISHED = 3;
  
  private int query;

  private GFont fontLeft,fontRight;

  public static final int [] commandArrangement = {
    Command.OK, Command.SCREEN,
    Command.ITEM, Command.BACK,
    Command.ITEM, Command.SCREEN,
    Command.OK, Command.ITEM,
    Command.ITEM, Command.CANCEL,
    Command.ITEM, Command.STOP,
    Command.OK, Command.BACK,
    Command.OK, Command.CANCEL,
    Command.OK, Command.STOP,
  };
  private int arrangement = 0;

  public SetupCanvas(Calc m) {
    midlet = m;

    ok  = new Command("ok",  Command.OK, 1);
    setCommandListener(this);

    menuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
    boldMenuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_MEDIUM);

    query = COMMAND_QUERY;
    setupHeading = commandQueryHeading;
    setupText = commandQueryText;

    alertString =
      "To adapt the user interface, please complete the following setup.";
    alertHeading = "Setup";

    String name = System.getProperty("microedition.platform");
    if (name != null) {
      if (name.startsWith("Nokia")) {
        arrangement = 1;
      } else if (name.indexOf("T610")>0 || name.indexOf("Z600")>0) {
        midlet.hasClearKey = true;
        midlet.commandArrangement = 0;
        midlet.bgrDisplay = false;
        query = QUERY_FINISHED;
        finish();
      } else if (name.indexOf("T630")>0) {
        midlet.hasClearKey = true;
        midlet.commandArrangement = 0;
        midlet.bgrDisplay = true;
        query = QUERY_FINISHED;
        finish();
      }
    }
  }

  public boolean isFinished() {
    return query == QUERY_FINISHED;
  }

  private void drawWrapped(Graphics g, int x, int y, int w, String text) {
    int start = 0;
    int end;
    Font f = g.getFont();
    while (start < text.length()) {
      end = text.length();
      while (end>start && f.substringWidth(text,start,end-start)>w)
        end = text.lastIndexOf(' ',end-1);
      if (end <= start) {
        // Must chop word
        end = text.indexOf(' ',start);
        if (end <= start)
          end = text.length();
        while (end>start && f.substringWidth(text,start,end-start)>w)
          end--;
      }
      g.drawSubstring(text,start,end-start,x,y,g.TOP|g.LEFT);
      y += f.getHeight();
      start = end;
      while (start < text.length() && text.charAt(start)==' ')
        start++;
    } 
  }

  public void paint(Graphics g) {
    if (yes!=null) removeCommand(yes);
    if (no !=null) removeCommand(no);
    if (left!=null) removeCommand(left);
    if (right!=null) removeCommand(right);
    removeCommand(ok);
    if (alertString != null) {
      addCommand(ok);
    } else if (query == COMMAND_QUERY) {
      no  = new Command("no",  commandArrangement[2*arrangement], 1);
      yes = new Command("yes", commandArrangement[2*arrangement+1], 1);
      addCommand(no);
      addCommand(yes);
    } else if (query == BGR_QUERY) {
      left = new Command("left",  commandArrangement[2*arrangement], 1);
      right = new Command("right", commandArrangement[2*arrangement+1], 1);
      addCommand(left);
      addCommand(right);
    }
    String text,heading;
    if (alertString != null) {
      g.setColor(216,156,156);
      text = alertString;
      heading = alertHeading;
    } else {
      g.setColor(156,216,216);
      text = setupText;
      heading = setupHeading;
    }
    g.fillRect(0,0,getWidth(),getHeight());
    g.setColor(0);
    g.setFont(boldMenuFont);
    g.drawString(heading,2,0,g.TOP|g.LEFT);
    g.setFont(menuFont);
    drawWrapped(g,2,boldMenuFont.getHeight()+3,getWidth()-3,text);
    if (alertString == null && query == BGR_QUERY) {
      if (fontLeft == null) {
        fontLeft = new GFont(GFont.MEDIUM);
        fontRight = new GFont(GFont.MEDIUM|GFont.BGR_ORDER);
      }
      fontLeft.drawString(g,2,getHeight()-fontLeft.getHeight()-2," 567 ");
      fontRight.drawString(g,getWidth()-fontRight.charWidth()*5-2,
                           getHeight()-fontRight.getHeight()-2," 567 ");
    }
  }

  private void clearKeyPressed(boolean hasClearKey) {
    midlet.hasClearKey = hasClearKey;
    if (midlet.display.isColor()) {
      alertString = "Thank you - next setup item";
      alertHeading = "Setup";
      query = BGR_QUERY;
      setupHeading = bgrQueryHeading;
      setupText = bgrQueryText;
    } else {
      alertString = "Thank you - setup finished";
      alertHeading = "Setup";
      query = QUERY_FINISHED;
    }
    repaint();
  }

  private void clearKeyInUse() {
    alertString = "Sorry, that key is used for something else";
    alertHeading = setupHeading;
    repaint();
  }

  private void nextCommandArrangement() {
    arrangement = (arrangement+1)%(commandArrangement.length/2);
    if (arrangement == 0)
      alertString = "All arrangements tried, trying first again";
    else
      alertString = "Okay, trying next key arrangement";
    alertHeading = setupHeading;
    repaint();
  }

  private void finish() {
    midlet.saveSetup();
    midlet.displayScreen();
  }

  protected void keyPressed(int key) {
    if (query == QUERY_FINISHED) {
      finish();
      return;
    }
    //if (alertString != null) {
    //  alertString = null;
    //  repaint();
    //  return;
    //}
    if (query == COMMAND_QUERY) {
      nextCommandArrangement();
      return;
    } else if (query == BGR_QUERY) {
      return;
    }
    switch (key) {
      case '0': case '1': case '2': case '3': case '4':
      case '5': case '6': case '7': case '8': case '9':
      case '*':
        clearKeyInUse();
        break;
      case '#':
        clearKeyPressed(false);
        break;
      default:
        switch (getGameAction(key)) {
          case UP:
          case DOWN:
          case LEFT:
          case RIGHT:
          case FIRE:
            clearKeyInUse();
            break;
          case GAME_A: case GAME_B: case GAME_C: case GAME_D:
            clearKeyPressed(true);
            break;
          default:
            // Some keys are not mapped to game keys and must be
            // handled directly in this dirty fashion...
            switch (key) {
              case -1: // UP
              case -2: // DOWN
              case -3: // LEFT
              case -4: // RIGHT
              case -5: // PUSH
                clearKeyInUse();
              default:
                clearKeyPressed(true);
                break;
            }
            break;
        }
        break;
    }
  }

  public void commandAction(Command c, Displayable d)
  {
    if (query == QUERY_FINISHED) {
      finish();
      return;
    }
    if (alertString != null) {
      alertString = null;
      repaint();
      return;
    }
    if (query == CLEAR_QUERY) {
      clearKeyInUse();
      return;
    }
    if (c == yes) {
      alertString = "Thank you - next setup item";
      alertHeading = "Setup";
      midlet.commandArrangement = (byte)arrangement;
      query = CLEAR_QUERY;
      setupHeading = clearQueryHeading;
      setupText = clearQueryText;
    } else if (c == no) {
      nextCommandArrangement();
    } else if (c == left || c == right) {
      alertString = "Thank you - setup finished";
      alertHeading = "Setup";
      query = QUERY_FINISHED;
      midlet.bgrDisplay = c == right;
    }
    repaint();
  }

}
