package ral;

import javax.microedition.lcdui.*;
import java.io.*;

public final class CalcCanvas
    extends MyCanvas
    implements CommandListener
{
// Commands:
//               ENTER  +  0-9a-f  .  -/E  clear  menu
// Modes:
//               DEG/RAD   FIX/SCI/ENG   BIN/OCT/HEX   BGN   PRG/RUN
// Menu:
//   basic    -> -  *  /  +/-  (repeat)
//   math     -> simple  -> 1/x    x^2    sqrt    %       %chg
//            -> pow     -> y^x    y^1/x  ln      e^x
//                       -> pow10/2 -> log10 10^x log2 2^x
//            -> prob    -> Py,x   Cy,x   x!      Gam(x)
//                       -> erfc   phi    inverfc invphi
//            -> misc    -> mod    div    random  factorize
//                       -> int -> round ceil floor trunc frac
//            -> matrix  -> new    concat stack   split
//                       -> more -> det A^T A*^T |A|_F Tr
//                               -> more -> size a_yx
//   trig     -> normal  -> sin    cos    tan
//            -> arc     -> asin   acos   atan
//            -> hyp     -> sinh   cosh   tanh
//            -> archyp  -> asinh  acosh  atanh
//            -> more    -> RAD/DEG ->RAD  ->DEG  pi
//                       -> coord   -> r->p   p->r   atan2   hypot   ->cplx
//                       -> cplx*   -> split  abs    arg     conj
//   bitop**  -> and     or     xor     bic
//   bitop2** -> not     y<<x   y>>x
//               int     -> round ceil floor trunc frac
//   special  -> stack   -> x<->y  clear  LASTx   undo
//                       -> more   -> rolldn rollup RCLst# x<->st#     ( -> # )
//            -> mem     -> STO#   STO+#  RCL#    x<->mem#               -> #
//            -> stat    -> SUM+   SUM-   clear
//                       -> result -> avg    -> x,y sx,sy dx,dy xw draw
//                                 -> ax+b   -> a,b x* y* r draw
//                                 -> alnx+b -> a,b x* y* r draw
//                                 -> be^ax  -> a,b x* y* r draw
//                                 -> bx^a   -> a,b x* y* r draw
//                       -> sums   -> n
//                                 -> x      -> SUMx SUMx� SUMlnx SUMln�x
//                                 -> y      -> SUMy SUMy� SUMlny SUMln�y
//                                 -> xy     -> SUMxy SUMxlny SUMylnx SUMlnxlny
//            -> finance -> STO RCL solve -> pv fv np pmt ir
//                       -> clear
//            -> conv    -> time   -> ->DH.MS  ->H  DH.MS+  now
//                                 -> more -> unix -> DH.MS->unix unix->DH.MS
//                                         -> JD   -> DH.MS->JD   JD->DH.MS
//                                         -> MJD  -> DH.MS->MJD  MJD->DH.MS
//                       -> metric -> length weight vol  energy temp
//                       -> const  -> univ   chem   phys atom   astro
//                       -> guess
//   mode     -> number  -> normal FIX#   SCI#   ENG#                  ( -> # )
//                       -> sepr   -> decimal  -> dot comma remove keep
//                                 -> thousand -> dot/comma space ' none
//            -> prog[1] -> run    -> #
//                       -> new    -> # -> name?
//                       -> append -> # 
//                       -> draw   -> y=f(x)      -> #
//                                 -> r=f(theta)  -> #
//                                 -> z=f(t)      -> #
//                       -> more   -> integrate   -> #
//                                 -> diff        -> #
//                                 -> solve       -> #
//                                 -> min/max     -> #
//                                 -> clear        -> #
//            -> prog[2] -> finish
//                       -> cond   -> x=y? x!=y? x<y? x<=y? x>y?
//                       -> util   -> abs max min sgn select
//                       -> reset
//                       -> mem    -> RCL[x] STO[x] STO+[x]
//            -> base    -> dec    hex    oct      bin
//            -> monitor -> mem    stat   finance  matrix  off (prog)            ( -> # )
//            -> sys     -> font   -> small  medium large  system
//                       -> exit
//                       -> reset
//                       -> fullscreen
//
// *  replaces coord if x or y are complex
// ** replaces math/trig in hex/oct/bin mode
//
// Future extentions:
//   special  -> conv    -> time   -> dow
//                       -> D->D.MS
//                       -> D.MS->D
//   math     -> misc    -> gcd
//            -> matrix  -> more   -> draw
//   mode     -> prog[1] -> draw   -> z=f(z)
//            -> sys     -> screen -> double-buffering

// Complex operations:
//   + - * / +/- 1/x x� sqrt
//   x==y? x!=y? select
//   <pow> <trig> <cplx> <stack> <mem> <int> <matrix>
//   prog/integrate prog/diff
//
// Matrix operations:
//   + - * / +/- 1/x x�
//   y^x cplx/conj
//   x==y? x!=y?
//   <matrix> <stack> <mem>
// Not yet matrix:
//   <int> select ->cplx cplx/split cplx/abs cplx/arg

// Physical constants:
// Universal
//   Speed of light in vacuum          c == 299792458 m/s
//   Planck constant                   h = 6.6260693e-34 J�s
//   Permeability of a vacuum          �_0 == 4*pi*1e-7 N/A�
//   Permittivity of a vacuum          eps_0 == 1/�_0�c� F/m
// Chemical
//   Avogadro constant                 N_A = 6.0221415e23 mol^-1
//   Molar gas constant                R = 8.314472 J/mol�K
//   Boltzmann constant                k = 1.3806505e-23 J/K
//   Faraday constant                  F = 96485.3383 C/mol
// Physical and electromagnetic
//   Fine-structure constant           alpha = 7.297352568e-3
//   Bohr radius                       a_0 = 5.291772108e-11 m
//   Rydberg constant                  R_inf = 10973731.568525m^-1
//   Bohr magneton                     �_B = 9.27400949e-24 J/T
// Atomic
//   Elementary charge                 e = 1.60217653e-19 C
//   Mass of electron                  m_e = 9.1093826e-31 kg
//   Mass of proton                    m_p = 1.67262171e-27 kg
//   Mass of neutron                   m_n = 1.67492728e-27 kg
//   Unified atomic mass unit          m_u = 1.66053886e-27 kg
// Astronomical
//   Newtonian constant of gravitation G = 6.6742e-11 N�m�/kg�
//   Standard acceleration of gravity  g_n == 9.80665 m/s�
//   Light year                        l.y. == 365.25*24*60*60 * c
//   Astronomical unit                 A.U. == 149597870691 m
//   Parsec                            pc == 360*60*60/2�pi * A.U.
//
// Metric conversions:
// Length
//   Inch in centimeters               in/cm == 2.54         (def)
//   Foot in meters                    ft/m == 0.3048        (ft = 12 in)
//   Yard in meters                    yd/m == 0.9144        (yd = 3 ft)
//   Mile in kilometers                mi/km == 1.609344     (mi = 5280 ft)
//   Nautical mile in kilometers       n.m./mk == 1.852      (def)
// Weight
//   U.S. pound in kilos               lb/kg == 0.45359237   (def)
//   U.S. ounce in grams               oz/g == 28.349523125  (oz = 1/16 lb)
//   U.S. short ton in kilos           ton/kg == 907.18474   (ton = 2000 lb)
//   U.S. grain in milligrams          gr/mg == 64.79891     (gr = 1/7000 lb)
// Volume
//   U.S. gallon in litres             gal/l == 3.785411784  (gal = 231 in�)
//   U.S. pint in litres               pt/l == 0.473176473   (pt = 1/8 gal)
//   U.S. cup in litres                cup/l == 0.2365882365 (cup = 1/2 pt)
//   U.S. fluid ounce in millilitres   fl.oz/ml==29.5735295625(fl.oz=gal/128)
// Temperature
//   Celsius to Fahrenheit             �C->�F == x*1.8+32    (def)
//   Fahrenheit to Celsius             �F->�C == (x-32)/1.8  (def)
//   Kelvin minus Celsius              �K-�C == 273.15       (def)
// Energy
//   International calorie in Joules   cal/J = 4.1868
//   British thermal unit in Joules    Btu/J = 1055.06
//   Horsepower in Watts               hp/W  = 745.7
//
// Sources: http://physics.nist.gov/cuu/Constants
//          http://www.free-definition.com
// (== means "equals exactly" or "equals by definition")

  private static final class Menu
  {
    public String label;
    public int command;
    public byte flags;
    public Menu [] subMenu;

    // get Details from CmdStr class
    Menu(int c) {
      label = CmdDesc.getStr(c, true);
      command = c;
      flags = CmdDesc.getFlags(c);
    }
    Menu(String l, int c) {
      label = l;
      command = c;
    }
    Menu(String l, int c, int f) {
      label = l;
      command = c;
      flags = (byte)f;
    }
    Menu(String l, Menu [] m) {
      label = l;
      subMenu = m;
    }
    Menu(String l, int f, Menu [] m) {
      label = l;
      flags = (byte)f;
      subMenu = m;
    }
  }

  private static final int EXIT = -999;
  private static final int RESET = -998;
  private static final int FULLSCREEN = -997;
  private static final int FONT_SMALL  = -50+GFont.SMALL;
  private static final int FONT_MEDIUM = -50+GFont.MEDIUM;
  private static final int FONT_LARGE  = -50+GFont.LARGE;
  private static final int FONT_XLARGE = -50+GFont.XLARGE;
  private static final int FONT_XXLARGE= -50+GFont.XXLARGE;
  private static final int FONT_XXXLARGE=-50+GFont.XXXLARGE;
  private static final int FONT_SYSTEM = -50+GFont.SYSTEM;
  private static final int NUMBER_0 = -20+0;
  private static final int NUMBER_1 = -20+1;
  private static final int NUMBER_2 = -20+2;
  private static final int NUMBER_3 = -20+3;
  private static final int NUMBER_4 = -20+4;
  private static final int NUMBER_5 = -20+5;
  private static final int NUMBER_6 = -20+6;
  private static final int NUMBER_7 = -20+7;
  private static final int NUMBER_8 = -20+8;
  private static final int NUMBER_9 = -20+9;
  private static final int NUMBER_10 = -20+10;
  private static final int NUMBER_11 = -20+11;
  private static final int NUMBER_12 = -20+12;
  private static final int NUMBER_13 = -20+13;
  private static final int NUMBER_14 = -20+14;
  private static final int NUMBER_15 = -20+15;

  private Menu basicMenu = new Menu("basic",new Menu[] {
    new Menu(CalcEngine.SUB),
    new Menu(CalcEngine.MUL),
    new Menu(CalcEngine.DIV),
    new Menu(CalcEngine.NEG),
    null,
  });

  private Menu enterMonitor =
    new Menu(CalcEngine.MONITOR_ENTER);

  private Menu systemMenu = new Menu("sys",new Menu[] {
    new Menu("font",new Menu[] {
      new Menu("medium",FONT_MEDIUM,CmdDesc.REPEAT_PARENT),
      new Menu("small",FONT_SMALL,CmdDesc.REPEAT_PARENT),
      new Menu("large",FONT_LARGE,CmdDesc.REPEAT_PARENT),
      new Menu("xlarge",FONT_XLARGE,CmdDesc.REPEAT_PARENT),
      new Menu("more",CmdDesc.TITLE_SKIP,new Menu [] {
        new Menu("xxlarge",FONT_XXLARGE,CmdDesc.REPEAT_PARENT),
        new Menu("sys",FONT_SYSTEM,CmdDesc.REPEAT_PARENT),
        null,
        new Menu("xxxlarge",FONT_XXXLARGE,CmdDesc.REPEAT_PARENT),
      }),
    }),
    new Menu("exit",EXIT,CmdDesc.NO_REPEAT),
    new Menu("reset",RESET,CmdDesc.NO_REPEAT),
    new Menu("fullscreen",FULLSCREEN,CmdDesc.NO_REPEAT),
    //new Menu("free",CalcEngine.FREE_MEM,CmdDesc.NO_REPEAT),
  });

  private Menu menu = new Menu("menu",new Menu[] {
    basicMenu,
    null, // math or binop
    null, // trig or binop2
    new Menu("special",new Menu [] {
      new Menu("stack",new Menu[] {
        new Menu(CalcEngine.LASTX),
        new Menu(CalcEngine.XCHG),
        new Menu(CalcEngine.UNDO),
        new Menu("more",CmdDesc.TITLE_SKIP,new Menu [] {
          new Menu(CalcEngine.RCLST),
          new Menu(CalcEngine.ROLLDN),
          new Menu(CalcEngine.ROLLUP),
          new Menu(CalcEngine.XCHGST),
        }),
        new Menu(CalcEngine.CLS),
      }),
      new Menu("mem",new Menu[] {
        new Menu(CalcEngine.STO),
        new Menu(CalcEngine.RCL),
        new Menu(CalcEngine.STP),
        new Menu(CalcEngine.XCHGMEM),
        new Menu(CalcEngine.CLMEM),
      }),
      new Menu("stat",new Menu[] {
        new Menu(CalcEngine.SUMPL),
        new Menu(CalcEngine.SUMMI),
        new Menu("result",CmdDesc.TITLE_SKIP,new Menu[] {
          new Menu("average",new Menu [] {
            new Menu(CalcEngine.AVG),
            new Menu(CalcEngine.STDEV),
            new Menu(CalcEngine.AVGXW),
            new Menu(CalcEngine.PSTDEV),
            new Menu(CalcEngine.AVG_DRAW),
          }),
          new Menu("ax+b",new Menu[] {
            new Menu(CalcEngine.LIN_AB),
            new Menu(CalcEngine.LIN_YEST),
            new Menu(CalcEngine.LIN_XEST),
            new Menu(CalcEngine.LIN_R),
            new Menu(CalcEngine.LIN_DRAW),
          }),
          new Menu("alnx+b",new Menu[] {
            new Menu(CalcEngine.LOG_AB),
            new Menu(CalcEngine.LOG_YEST),
            new Menu(CalcEngine.LOG_XEST),
            new Menu(CalcEngine.LOG_R),
            new Menu(CalcEngine.LOG_DRAW),
          }),
          new Menu("be^ax",new Menu[] {
            new Menu(CalcEngine.EXP_AB),
            new Menu(CalcEngine.EXP_YEST),
            new Menu(CalcEngine.EXP_XEST),
            new Menu(CalcEngine.EXP_R),
            new Menu(CalcEngine.EXP_DRAW),
          }),
          new Menu("bx^a",new Menu[] {
            new Menu(CalcEngine.POW_AB),
            new Menu(CalcEngine.POW_YEST),
            new Menu(CalcEngine.POW_XEST),
            new Menu(CalcEngine.POW_R),
            new Menu(CalcEngine.POW_DRAW),
          }),
        }),
        new Menu("sums",new Menu[] {
          new Menu(CalcEngine.N),
          new Menu("x",CmdDesc.TITLE_SKIP,new Menu[] {
            new Menu(CalcEngine.SUMX),
            new Menu(CalcEngine.SUMXX),
            new Menu(CalcEngine.SUMLNX),
            new Menu(CalcEngine.SUMLN2X),
          }),
          new Menu("y",CmdDesc.TITLE_SKIP,new Menu[] {
            new Menu(CalcEngine.SUMY),
            new Menu(CalcEngine.SUMYY),
            new Menu(CalcEngine.SUMLNY),
            new Menu(CalcEngine.SUMLN2Y),
          }),
          new Menu("xy",CmdDesc.TITLE_SKIP,new Menu[] {
            new Menu(CalcEngine.SUMXY),
            new Menu(CalcEngine.SUMXLNY),
            new Menu(CalcEngine.SUMYLNX),
            new Menu(CalcEngine.SUMLNXLNY),
          }),
        }),
        new Menu(CalcEngine.CLST),
      }),
      new Menu("finance",new Menu [] {
        new Menu(CalcEngine.FINANCE_STO),
        new Menu(CalcEngine.FINANCE_RCL),
        new Menu(CalcEngine.FINANCE_SOLVE),
        new Menu("more",CmdDesc.TITLE_SKIP,new Menu [] {
          new Menu(CalcEngine.FINANCE_BGNEND),
          new Menu(CalcEngine.FINANCE_MULINT),
          new Menu(CalcEngine.FINANCE_DIVINT),
        }),
        new Menu(CalcEngine.FINANCE_CLEAR),
      }),
      new Menu("conv",new Menu [] {
        new Menu("time",new Menu[] {
          new Menu(CalcEngine.TO_DHMS),
          new Menu(CalcEngine.TO_H),
          new Menu(CalcEngine.TIME_NOW),
          new Menu(CalcEngine.DHMS_PLUS),
          new Menu("more",CmdDesc.TITLE_SKIP,new Menu [] {
            new Menu("unix",CmdDesc.TITLE_SKIP,new Menu [] {
              new Menu(CalcEngine.DHMS_TO_UNIX),
              null,
              null,
              new Menu(CalcEngine.UNIX_TO_DHMS),
            }),
            new Menu("JD",CmdDesc.TITLE_SKIP,new Menu [] {
              new Menu(CalcEngine.DHMS_TO_JD),
              null,
              null,
              new Menu(CalcEngine.JD_TO_DHMS),
            }),
            new Menu("MJD",CmdDesc.TITLE_SKIP,new Menu [] {
              new Menu(CalcEngine.DHMS_TO_MJD),
              null,
              null,
              new Menu(CalcEngine.MJD_TO_DHMS),
            }),
            new Menu(CalcEngine.TIME),
            new Menu(CalcEngine.DATE),
          }),
        }),
        new Menu("metric",new Menu [] {
          new Menu("length",new Menu [] {
            new Menu(CalcEngine.CONST_km_mi),
            new Menu(CalcEngine.CONST_cm_in),
            new Menu(CalcEngine.CONST_m_yd),
            new Menu(CalcEngine.CONST_km_nm),
            new Menu(CalcEngine.CONST_m_ft),
          }),
          new Menu("weight",new Menu [] {
            new Menu(CalcEngine.CONST_g_oz),
            new Menu(CalcEngine.CONST_kg_lb),
            new Menu(CalcEngine.CONST_mg_gr),
            new Menu(CalcEngine.CONST_kg_ton),
          }),
          new Menu("temp",new Menu [] {
            new Menu(CalcEngine.CONV_C_F),
            new Menu(CalcEngine.CONV_F_C),
            new Menu(CalcEngine.CONST_K_C),
          }),
          new Menu("energy",new Menu [] {
            new Menu(CalcEngine.CONST_J_cal),
            new Menu(CalcEngine.CONST_J_Btu),
            new Menu(CalcEngine.CONST_W_hp),
          }),
          new Menu("vol",new Menu [] {
            new Menu(CalcEngine.CONST_l_pt),
            new Menu(CalcEngine.CONST_l_cup),
            new Menu(CalcEngine.CONST_l_gal),
            new Menu(CalcEngine.CONST_ml_floz),
          }),
        }),
        new Menu("const",new Menu [] {
          new Menu("univ",new Menu [] {
            new Menu(CalcEngine.CONST_c),
            new Menu(CalcEngine.CONST_h),
            new Menu(CalcEngine.CONST_mu_0),
            new Menu(CalcEngine.CONST_eps_0),
          }),
          new Menu("chem",new Menu [] {
            new Menu(CalcEngine.CONST_NA),
            new Menu(CalcEngine.CONST_R),
            new Menu(CalcEngine.CONST_k),
            new Menu(CalcEngine.CONST_F),
          }),
          new Menu("phys",new Menu [] {
            new Menu(CalcEngine.CONST_alpha),
            new Menu(CalcEngine.CONST_a_0),
            new Menu(CalcEngine.CONST_R_inf),
            new Menu(CalcEngine.CONST_mu_B),
          }),
          new Menu("atom",new Menu [] {
            new Menu(CalcEngine.CONST_e),
            new Menu(CalcEngine.CONST_m_e),
            new Menu(CalcEngine.CONST_m_p),
            new Menu(CalcEngine.CONST_m_n),
            new Menu(CalcEngine.CONST_m_u),
          }),
          new Menu("astro",new Menu [] {
            new Menu(CalcEngine.CONST_G),
            new Menu(CalcEngine.CONST_g_n),
            new Menu(CalcEngine.CONST_ly),
            new Menu(CalcEngine.CONST_AU),
            new Menu(CalcEngine.CONST_pc),
          }),
        }),
        new Menu(CalcEngine.GUESS),
      }),
    }),
    new Menu("mode",new Menu[] {
      new Menu("number",new Menu[] {
        new Menu(CalcEngine.NORM),
        new Menu(CalcEngine.FIX),
        new Menu(CalcEngine.SCI),
        new Menu(CalcEngine.ENG),
        new Menu("sepr",new Menu[] {
          new Menu("point",new Menu[] {
            new Menu(CalcEngine.POINT_DOT),
            new Menu(CalcEngine.POINT_COMMA),
            new Menu(CalcEngine.POINT_KEEP),
            new Menu(CalcEngine.POINT_REMOVE),
          }),
          null,
          null,
          new Menu("thousand",new Menu[] {
            new Menu(CalcEngine.THOUSAND_DOT),
            new Menu(CalcEngine.THOUSAND_SPACE),
            new Menu(CalcEngine.THOUSAND_QUOTE),
            new Menu(CalcEngine.THOUSAND_NONE),
          }),
        }),
      }),
      null, // prog1 or prog2
      new Menu("base",new Menu[] {
        new Menu(CalcEngine.BASE_DEC),
        new Menu(CalcEngine.BASE_HEX),
        new Menu(CalcEngine.BASE_OCT),
        new Menu(CalcEngine.BASE_BIN),
      }),
      new Menu("monitor",new Menu[] {
        new Menu(CalcEngine.MONITOR_FINANCE),
        new Menu(CalcEngine.MONITOR_STAT),
        new Menu(CalcEngine.MONITOR_MEM),
        new Menu(CalcEngine.MONITOR_MATRIX),
        null, // off or prog
      }),
      systemMenu,
    }),
  });
  
  private Menu monitorOffMenu = new Menu(CalcEngine.MONITOR_NONE);
  private Menu monitorProgMenu = new Menu(CalcEngine.MONITOR_PROG);

  private Menu numberMenu = new Menu(null,new Menu[] {
    new Menu("<0-3>",CmdDesc.TITLE_SKIP|CmdDesc.REPEAT_PARENT,new Menu[] {
      new Menu("<0>",NUMBER_0,CmdDesc.REPEAT_PARENT),
      new Menu("<1>",NUMBER_1,CmdDesc.REPEAT_PARENT),
      new Menu("<2>",NUMBER_2,CmdDesc.REPEAT_PARENT),
      new Menu("<3>",NUMBER_3,CmdDesc.REPEAT_PARENT),
    }),
    new Menu("<4-7>",CmdDesc.TITLE_SKIP|CmdDesc.REPEAT_PARENT,new Menu[] {
      new Menu("<4>",NUMBER_4,CmdDesc.REPEAT_PARENT),
      new Menu("<5>",NUMBER_5,CmdDesc.REPEAT_PARENT),
      new Menu("<6>",NUMBER_6,CmdDesc.REPEAT_PARENT),
      new Menu("<7>",NUMBER_7,CmdDesc.REPEAT_PARENT),
    }),
    new Menu("<8-11>",CmdDesc.TITLE_SKIP|CmdDesc.REPEAT_PARENT,new Menu[] {
      new Menu("<8>",NUMBER_8,CmdDesc.REPEAT_PARENT),
      new Menu("<9>",NUMBER_9,CmdDesc.REPEAT_PARENT),
      new Menu("<10>",NUMBER_10,CmdDesc.REPEAT_PARENT),
      new Menu("<11>",NUMBER_11,CmdDesc.REPEAT_PARENT),
    }),
    new Menu("<12-15>",CmdDesc.TITLE_SKIP|CmdDesc.REPEAT_PARENT,new Menu[] {
      new Menu("<12>",NUMBER_12,CmdDesc.REPEAT_PARENT),
      new Menu("<13>",NUMBER_13,CmdDesc.REPEAT_PARENT),
      new Menu("<14>",NUMBER_14,CmdDesc.REPEAT_PARENT),
      new Menu("<15>",NUMBER_15,CmdDesc.REPEAT_PARENT),
    }),
  });

  private Menu financeMenu = new Menu(null,new Menu[] {
    new Menu("pv" ,NUMBER_0,CmdDesc.REPEAT_PARENT),
    new Menu("fv" ,NUMBER_1,CmdDesc.REPEAT_PARENT),
    new Menu("np" ,NUMBER_2,CmdDesc.REPEAT_PARENT),
    new Menu("pmt",NUMBER_3,CmdDesc.REPEAT_PARENT),
    new Menu("ir%",NUMBER_4,CmdDesc.REPEAT_PARENT),
  });

  private Menu intMenu = new Menu("int",new Menu[] {
    new Menu(CalcEngine.ROUND),
    new Menu(CalcEngine.CEIL),
    new Menu(CalcEngine.FLOOR),
    new Menu(CalcEngine.TRUNC),
    new Menu(CalcEngine.FRAC),
  });

  private Menu math = new Menu("math",new Menu[] {
    new Menu("simple",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.SQRT),
      new Menu(CalcEngine.SQR),
      new Menu(CalcEngine.RECIP),
      new Menu(CalcEngine.PERCENT_CHG),
      new Menu(CalcEngine.PERCENT),
    }),
    new Menu("pow",new Menu[] {
      new Menu(CalcEngine.EXP),
      new Menu(CalcEngine.YPOWX),
      new Menu(CalcEngine.LN),
      new Menu(CalcEngine.XRTY),
      new Menu("pow_10,2",CmdDesc.TITLE_SKIP,new Menu[] {
        new Menu(CalcEngine.EXP2),
        new Menu(CalcEngine.EXP10),
        new Menu(CalcEngine.LOG2),
        new Menu(CalcEngine.LOG10),
      }),
    }),
    new Menu("misc",new Menu[] {
      new Menu(CalcEngine.RANDOM),
      new Menu(CalcEngine.MOD),
      new Menu(CalcEngine.DIVF),
      new Menu(CalcEngine.FACTORIZE),
      intMenu,
    }),
    new Menu("matrix",new Menu[] {
      new Menu(CalcEngine.MATRIX_NEW),
      new Menu(CalcEngine.MATRIX_STACK),
      new Menu(CalcEngine.MATRIX_SPLIT),
      new Menu(CalcEngine.MATRIX_CONCAT),
      new Menu("more",CmdDesc.TITLE_SKIP,new Menu[] {
        new Menu(CalcEngine.DETERM),
        new Menu(CalcEngine.TRANSP),
        new Menu(CalcEngine.TRACE),
        new Menu(CalcEngine.TRANSP_CONJ),
        new Menu("more",CmdDesc.TITLE_SKIP,new Menu[] {
          new Menu(CalcEngine.ABS),
          new Menu(CalcEngine.MATRIX_SIZE),
          new Menu(CalcEngine.MATRIX_AIJ),
        }),
      }),
    }),
    new Menu("prob",new Menu[] {
      new Menu(CalcEngine.PYX),
      new Menu(CalcEngine.CYX),
      new Menu(CalcEngine.FACT),
      new Menu(CalcEngine.GAMMA),
      new Menu("more",CmdDesc.TITLE_SKIP,new Menu[] {
        new Menu(CalcEngine.INVERFC),
        new Menu(CalcEngine.ERFC),
        new Menu(CalcEngine.PHI),
        new Menu(CalcEngine.INVPHI),
      }),
    }),
  });

  private Menu trig = new Menu("trig",new Menu[] {
    new Menu("normal",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.SIN),
      new Menu(CalcEngine.COS),
      new Menu(CalcEngine.TAN),
    }),
    new Menu("arc",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.ASIN),
      new Menu(CalcEngine.ACOS),
      new Menu(CalcEngine.ATAN),
    }),
    new Menu("hyp",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.SINH),
      new Menu(CalcEngine.COSH),
      new Menu(CalcEngine.TANH),
    }),
    new Menu("archyp",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.ASINH),
      new Menu(CalcEngine.ACOSH),
      new Menu(CalcEngine.ATANH),
    }),
    new Menu("more",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.TRIG_DEGRAD),
      new Menu(CalcEngine.TO_RAD),
      new Menu(CalcEngine.TO_DEG),
      null, // coord or cplx
      new Menu(CalcEngine.PI),
    }),
  });

  private Menu coordMenu = new Menu("coord",new Menu[] {
    new Menu(CalcEngine.HYPOT),
    new Menu(CalcEngine.RP),
    new Menu(CalcEngine.PR),
    new Menu(CalcEngine.ATAN2),
    new Menu(CalcEngine.TO_CPLX),
  });

  private Menu cplxMenu = new Menu("cplx",new Menu[] {
    new Menu(CalcEngine.CPLX_SPLIT),
    new Menu(CalcEngine.ABS),
    new Menu(CalcEngine.CPLX_ARG),
    new Menu(CalcEngine.CPLX_CONJ),
  });

  private Menu bitOp = new Menu("bitop",new Menu[] {
    new Menu(CalcEngine.AND),
    new Menu(CalcEngine.OR),
    new Menu(CalcEngine.BIC),
    new Menu(CalcEngine.XOR),
  });
  private Menu bitMath = new Menu("bitop_2",new Menu[] {
    new Menu(CalcEngine.NOT),
    new Menu(CalcEngine.YUPX),
    new Menu(CalcEngine.YDNX),
    intMenu,
  });

  private Menu prog1 = new Menu("prog",new Menu[] {
    new Menu(CalcEngine.PROG_NEW),
    new Menu(CalcEngine.PROG_RUN),
    new Menu("draw",CmdDesc.NO_PROG,new Menu[] {
      new Menu(CalcEngine.PROG_DRAW),
      new Menu(CalcEngine.PROG_DRAWPOL),
      new Menu(CalcEngine.PROG_DRAWPARM),
      new Menu(CalcEngine.PROG_DRAWZZ),
    }),
    new Menu(CalcEngine.PROG_APPEND),
    new Menu("more",CmdDesc.TITLE_SKIP,new Menu[] {
      new Menu(CalcEngine.PROG_INTEGR),
      new Menu(CalcEngine.PROG_DIFF),
      new Menu(CalcEngine.PROG_SOLVE),
      new Menu(CalcEngine.PROG_MINMAX),
      new Menu(CalcEngine.PROG_CLEAR),
    }),
  });

  private Menu prog2 = new Menu("prog",new Menu[] {
    new Menu(CalcEngine.PROG_FINISH),
    new Menu("cond",new Menu[] {
      new Menu(CalcEngine.IF_EQUAL),
      new Menu(CalcEngine.IF_NEQUAL),
      new Menu(CalcEngine.IF_LESS),
      new Menu(CalcEngine.IF_LEQUAL),
      new Menu(CalcEngine.IF_GREATER),
    }),
    new Menu("util",new Menu[] {
      new Menu(CalcEngine.ABS),
      new Menu(CalcEngine.MAX),
      new Menu(CalcEngine.MIN),
      new Menu(CalcEngine.SELECT),
      new Menu("sgn",CalcEngine.SGN),
    }),
    new Menu(CalcEngine.PROG_PURGE),
    new Menu("mem",new Menu[] {
      new Menu(CalcEngine.RCL_X),
      new Menu(CalcEngine.STO_X),
      null,
      new Menu(CalcEngine.STP_X),
    }),
  });

  private Menu progMenu = new Menu(null,new Menu[] {
    new Menu("",NUMBER_0,CmdDesc.REPEAT_PARENT),
    new Menu("",NUMBER_1,CmdDesc.REPEAT_PARENT),
    new Menu("",NUMBER_2,CmdDesc.REPEAT_PARENT),
    new Menu("",NUMBER_3,CmdDesc.REPEAT_PARENT),
    new Menu("more",CmdDesc.TITLE_SKIP|CmdDesc.REPEAT_PARENT,new Menu[] {
      new Menu("",NUMBER_4,CmdDesc.REPEAT_PARENT),
      new Menu("",NUMBER_5,CmdDesc.REPEAT_PARENT),
      new Menu("",NUMBER_6,CmdDesc.REPEAT_PARENT),
      new Menu("",NUMBER_7,CmdDesc.REPEAT_PARENT),
      new Menu("",NUMBER_8,CmdDesc.REPEAT_PARENT),
      // Remember to set CalcEngine.NUM_PROGS accordingly
    }),
  });

  private static final int menuColor [] = {
    0x00e0e0,0x00fc00,0xe0e000,0xfca800,0xfc5400,0xfc0000,
  };

  private Font menuFont;
  private Font boldMenuFont;
  private Font smallMenuFont;
  private Font smallBoldMenuFont;
  private GFont numberFont;
  private int numberFontStyle;
  public boolean fullScreen;
  public CalcEngine calc;
  public static CalcCanvas canvas;

  private Command add;
  private Command enter;

  private final Calc midlet;

  private int numRepaintLines;
  private boolean repeating = false;
  private boolean unknownKeyPressed = false;
  private boolean internalRepaint = false;
  private int offX, offY, nDigits, nLines, numberWidth, numberHeight;
  private int offY2, offYMonitor, nLinesMonitor;
  private boolean evenFrame = true;
  private int menuX,menuY,menuW,menuH;
  private int header,footer;

  private Menu [] menuStack;
  private int menuStackPtr;
  private int menuCommand;
  private Menu menuItem;
  private Menu repeatedMenuItem;

  public CalcCanvas(Calc m, DataInputStream in) {
    canvas = this;
    midlet = m;

    calc = new CalcEngine();

    numberFontStyle = (getWidth()>=320 ? GFont.XXXLARGE :
                       getWidth()>=256 ? GFont.XXLARGE :
                       getWidth()>=160 ? GFont.XLARGE :
                       getWidth()>=128 ? GFont.LARGE :
                       getWidth()>=96  ? GFont.MEDIUM : GFont.SMALL);
    if (in != null)
      restoreState(in);
    calc.initialized = true;

    if (!midlet.display.isColor()) {
      numberFontStyle = GFont.SYSTEM;
      // Now, remove the font menu.
      systemMenu.subMenu[0] = null;
    }
    if (!canToggleFullScreen()) {
      // Remove the fullscreen command.
      systemMenu.subMenu[3] = null;
    }

    setCommands("ENTER","+");
    setCommandListener(this);

    menuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
    boldMenuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_MEDIUM);
    smallMenuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL);
    smallBoldMenuFont = Font.getFont(
      Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_SMALL);
    header = smallMenuFont.getHeight();
    footer = automaticCommands() ? 0 : boldMenuFont.getHeight()+1;
    setNumberFont(numberFontStyle);

    setFullScreen(fullScreen);

    menuStack = new Menu[6]; // One too many, I think
    menuStackPtr = -1;

    numRepaintLines = 100;
    checkRepaint();
  }

  private void setCommands(String enterStr, String addStr) {
    if (enter != null)
      removeCommand(enter);
    if (add != null)
      removeCommand(add);

    if ((midlet.commandArrangement & 0x80) == 0) {
      enter = new Command(enterStr,
        SetupCanvas.commandArrangement[(midlet.commandArrangement&0x7f)*2], 1);
      add   = new Command(addStr,
       SetupCanvas.commandArrangement[(midlet.commandArrangement&0x7f)*2+1],1);
      addCommand(enter);
      addCommand(add);
    } else {
      // Reverse order
      add   = new Command(addStr,
        SetupCanvas.commandArrangement[(midlet.commandArrangement&0x7f)*2], 1);
      enter = new Command(enterStr,
       SetupCanvas.commandArrangement[(midlet.commandArrangement&0x7f)*2+1],1);
      addCommand(add);
      addCommand(enter);
    }
  }

  protected void sizeChanged(int w, int h) {
    // Menu position
    menuW = 21+4*2;
    if (menuW<boldMenuFont.stringWidth("m/ft")+3*2)
      menuW = boldMenuFont.stringWidth("m/ft")+3*2;
    menuW = boldMenuFont.stringWidth("acosh")*2+3*2+menuW;
    if (menuW<(menuFont.stringWidth("thousand")+2*2)*2)
      menuW = (menuFont.stringWidth("thousand")+2*2)*2;
    if (menuW>getWidth()) menuW = getWidth();
    menuH = menuFont.getHeight()*2+3*2+5*2+21;
    if (menuH>getHeight()) menuH = getHeight();
    menuX = (getWidth()-menuW)/2;
    menuY = header+(getHeight()-menuH-header-footer)/2;
    if (menuY-menuFont.getHeight()-1<header)
      menuY = header+menuFont.getHeight()+1;
    if (menuY+menuH > getHeight())
      menuY = getHeight()-menuH;

    // Number font
    nDigits = getWidth()/numberWidth;
    offX = (getWidth()-nDigits*numberWidth)/2;
    nLines = (getHeight()-header-footer)/numberHeight;
    offY = (getHeight()-header-footer-nLines*numberHeight)/2 + header;
    nLinesMonitor = (getHeight()-header-footer-4)/numberHeight;
    offYMonitor = (getHeight()-header-footer-
                   nLinesMonitor*numberHeight)/4+header;
    offY2 = 3*(getHeight()-header-footer-
               nLinesMonitor*numberHeight)/4+header;
    calc.setMaxWidth(nDigits);
    calc.setMaxMonitorSize(nLinesMonitor-1);
  }

  public void saveState(DataOutputStream out) {
    try {
      numberFontStyle = numberFont.getStyle();
      numberFont = null; // Free some memory before saveSqtate()
      out.writeShort(1+1);
      out.writeByte(numberFontStyle);
      out.writeBoolean(fullScreen);
      calc.command(CalcEngine.FINALIZE,0);
      calc.saveState(out);
    } catch (Throwable e) {
    }
  }
  
  private void restoreState(DataInputStream in) {
    try {
      short length = in.readShort();
      if (length >= 1) {
        numberFontStyle = in.readByte();
        length -= 1;
      }
      if (length >= 1) {
        fullScreen = in.readBoolean();
        length -= 1;
      }
      in.skip(length);
      calc.restoreState(in);
    } catch (IOException ioe) {
    }
  }

  private void setNumberFont(int size) {
    numberFont = null;
    try {
      numberFont = GFont.
        getFont(size | (midlet.bgrDisplay ? GFont.BGR_ORDER : 0), true, this);
    } catch (OutOfMemoryError e) {
      // Fallback to System font
      numberFont = GFont.
        getFont(GFont.SYSTEM | (midlet.bgrDisplay ? GFont.BGR_ORDER : 0),
                true, this);
      midlet.outOfMemory();
    }
    numberWidth = numberFont.charWidth();
    numberHeight = numberFont.getHeight();
    sizeChanged(getWidth(),getHeight());
  }

  public void drawModeIndicators(Graphics g, boolean toggleRun, boolean stop) {
    g.setColor(0xffffff);
    g.fillRect(0,0,getWidth(),header-1);
    g.setColor(0);
    g.setFont(smallMenuFont);
    int n = 4;
    if (calc.begin && (calc.progRecording || calc.progRunning))
      n = 5;

    int w = smallMenuFont.stringWidth("ENG");
    int x = getWidth()/(n*2)-w/2;
    if (x<0) x=0;
    
    if (calc.degrees)
      g.drawString("DEG",x,0,g.TOP|g.LEFT);
    else
      g.drawString("RAD",x,0,g.TOP|g.LEFT);

    x += Math.max(getWidth()/n,w+2);
      
    if (calc.format.fse == Real.NumberFormat.FSE_FIX)
      g.drawString("FIX",x,0,g.TOP|g.LEFT);
    else if (calc.format.fse == Real.NumberFormat.FSE_SCI)
      g.drawString("SCI",x,0,g.TOP|g.LEFT);
    else if (calc.format.fse == Real.NumberFormat.FSE_ENG)
      g.drawString("ENG",x,0,g.TOP|g.LEFT);

    x += Math.max(getWidth()/n,w+2);

    if (calc.format.base == 2)
      g.drawString("BIN",x,0,g.TOP|g.LEFT);
    else if (calc.format.base == 8)
      g.drawString("OCT",x,0,g.TOP|g.LEFT);
    else if (calc.format.base == 16)
      g.drawString("HEX",x,0,g.TOP|g.LEFT);

    x += Math.max(getWidth()/n,w+2);

    if (calc.begin)
      g.drawString("BGN",x,0,g.TOP|g.LEFT);

    if (n == 5)
      x += Math.max(getWidth()/n,w+2);

    if (calc.progRecording)
      g.drawString("PRG",x,0,g.TOP|g.LEFT);
    else if (stop) {
      w = smallMenuFont.stringWidth("STOP");
      if (x+w-1>getWidth())
        x = getWidth()-w-1;
      g.drawString("STOP",x,0,g.TOP|g.LEFT);      
    } else if (calc.progRunning && (evenFrame || !toggleRun))
      g.drawString("RUN",x,0,g.TOP|g.LEFT);
    if (toggleRun)
      evenFrame = !evenFrame;
  }

  private void clearScreen(Graphics g) {
    // Clear screen and draw mode indicators
    drawModeIndicators(g,false,false);
    g.setColor(0);
    g.fillRect(0,header-1,getWidth(),
               getHeight()-header-footer+1);
    if (!automaticCommands())
      paintCommands(g,boldMenuFont,calc.isInsideMonitor ? "move"
                    : "menu", menuFont);
  }

  private boolean plainLabel(String label) {
    for (int i=0; i<label.length(); i++)
      if ("^~_���ߡ�����".indexOf(label.charAt(i))>=0)
        return false;
    return true;
  }

  public static int getBaselinePosition(Font f) {
    int b = f.getBaselinePosition();
    if (b < f.getHeight()/2) // Obviously wrong
      return f.getHeight()*19/22;
    return b;
  }

  private int labelWidth(String label, boolean bold) {
    Font normalFont = bold ? boldMenuFont : menuFont;
    Font smallFont = bold ? smallBoldMenuFont : smallMenuFont;
    if (plainLabel(label))
      return normalFont.stringWidth(label);
    int width = 0;
    Font font = normalFont;
    for (int i=0; i<label.length(); i++) {
      char c = label.charAt(i);
      if (c=='^' || c=='_')
        font = font==normalFont ? smallFont : normalFont;
      else if (c=='~')
        ; // overline... no font change
      else if ("���ߡ��".indexOf(c)>=0)
        width += font.charWidth('O');
      else if (c=='�')
        width += font.charWidth('o')*(6+4)/6;
      else if (c=='�') {
        int h2 = (getBaselinePosition(font)*2/3+1)&~1;
        width += h2/2+5+(h2<10?0:1);
      } else if (c=='�')
        width += font.charWidth('e')*67/112 + 1;
      else
        width += font.charWidth(c);
    }
    return width;
  }

  private void drawLabel(Graphics g, String label, boolean bold, int x,int y) {
    Font normalFont = bold ? boldMenuFont : menuFont;
    Font smallFont = bold ? smallBoldMenuFont : smallMenuFont;
    Font font = normalFont;
    g.setFont(font);
    if (plainLabel(label)) {
      g.drawString(label,x,y,g.TOP|g.LEFT);
      return;
    }
    boolean sub=false,sup=false,overline=false;
    for (int i=0; i<label.length(); i++) {
      char c = label.charAt(i);
      if (c=='^' || c=='_') {
        font = font==normalFont ? smallFont : normalFont;
        g.setFont(font);
        sub = sup = false;
        if (font == smallFont) {
          sub = c=='_';
          sup = c=='^';
        }
      } else if (c=='~') {
        overline = !overline;
      } else if ("���ߡ�����".indexOf(c)>=0) {
        int w = font.charWidth('O');
        int h = getBaselinePosition(font);
        switch (c) {
          case '�': // Arrow ->
            g.drawLine(x,y+h/2+1,x+w-2,y+h/2+1);
            g.drawLine(x,y+h/2+2,x+w-2,y+h/2+2);
            g.drawLine(x+w-2,y+h/2+1,x+w-2-2,y+h/2+1-2);
            g.drawLine(x+w-3,y+h/2+1,x+w-3-1,y+h/2+1-1);
            g.drawLine(x+w-2,y+h/2+2,x+w-2-2,y+h/2+2+2);
            g.drawLine(x+w-3,y+h/2+2,x+w-3-1,y+h/2+2+1);
            break;
          case '�': // Arrows <->
            g.drawLine(x,y+h/2,x+w-2,y+h/2);
            g.drawLine(x+w-2,y+h/2,x+w-2-2,y+h/2-2);
            g.drawLine(x,y+h/2+3,x+w-2,y+h/2+3);
            g.drawLine(x,y+h/2+3,x+2,y+h/2+3+2);
            if (bold) {
              g.drawLine(x,y+h/2-1,x+w-2,y+h/2-1);
              g.drawLine(x+w-2,y+h/2-1,x+w-2-2,y+h/2-1-2);
              g.drawLine(x,y+h/2+2,x+w-2,y+h/2+2);
              g.drawLine(x,y+h/2+2,x+2,y+h/2+2+2);
            }
            break;
          case '�': // Sqrt
            g.drawLine(x,y+h-3,x+3,y+h);
            g.drawLine(x+1,y+h-3,x+4,y+h);
            g.drawLine(x+3,y,x+3,y+h);
            g.drawLine(x+4,y,x+4,y+h);
            g.drawLine(x+3,y,x+w,y);
            g.drawLine(x+3,y+1,x+w,y+1);
            overline = true;
            break;
          case '�': // Sum
            int b = (h&1)^1;
            int s = (h-b-4)/2;
            g.drawLine(x,y+b,x+w-2,y+b);
            g.drawLine(x,y+b+1,x+w-2,y+b+1);
            g.drawLine(x,y+b+2,x+s,y+b+2+s);
            g.drawLine(x+1,y+b+2,x+1+s,y+b+2+s);
            g.drawLine(x,y+h-3,x+s,y+b+2+s);
            g.drawLine(x+1,y+h-3,x+1+s,y+b+2+s);
            g.drawLine(x,y+h-1,x+w-2,y+h-1);
            g.drawLine(x,y+h-2,x+w-2,y+h-2);
            break;
          case '�': // Gamma
            g.drawLine(x+1,y,x+1,y+h-1);
            g.drawLine(x+2,y,x+2,y+h-1);
            g.drawLine(x,y+h-1,x+3,y+h-1);
            g.drawLine(x,y,x+w-2,y);
            g.drawLine(x,y+1,x+w-2,y+1);
            g.drawLine(x+w-2,y,x+w-2,y+3);
            break;
          case '�': // pi
            g.drawLine(x,y+h/3,x+7,y+h/3);
            g.drawLine(x+2,y+h/3,x+2,y+h-1);
            g.drawLine(x+3,y+h/3,x+3,y+h-1);
            g.drawLine(x,y+h/3,x,y+h/3+1);
            g.drawLine(x+5,y+h/3,x+5,y+h-1);
            g.drawLine(x+6,y+h/3,x+6,y+h-1);
            break;
          case '�': // _infinity
            g.drawChar('o',x,y+normalFont.getHeight()-
                       getBaselinePosition(smallFont),g.TOP|g.LEFT);
            g.drawChar('o',x+font.charWidth('o')*4/6,
                       y+normalFont.getHeight()-
                       getBaselinePosition(smallFont),g.TOP|g.LEFT);
            w = font.charWidth('o')*(6+4)/6;
            break;
          case '�': // alpha
            int h2 = (h*2/3+1)&~1;
            w = h2/2+5+(h2<10?0:1);
            int x2 = x+w-h2/2;
            int y2 = y+h-h2;
            g.drawLine(x+w-2,y2,x2,y+h-3);
            g.drawLine(x+w-3,y2,x2-1,y+h-3);
            g.drawLine(x2-2,y+h-3,x2-1,y+h-2);
            g.fillRect(x2-4,y+h-2,3,2);
            g.drawLine(x2-5,y+h-2,x2-4,y+h-3);
            if (h2<10) {
              g.fillRect(x2-6,y2+2,2,y+h-y2-4);
            } else {
              int h3 = (h2-1)/4;
              g.fillRect(x2-6,y2+2,2,h3);
              g.fillRect(x2-7,y2+2+h3,2,y+h-y2-4-2*h3);
              g.fillRect(x2-6,y+h-2-h3,2,h3);
            }
            g.drawLine(x2-5,y2+1,x2-4,y2+2);
            g.fillRect(x2-4,y2,2,2);
            g.drawLine(x2-2,y2+2,x2-2,y2+1);
            g.drawLine(x2-1,y2+2,x+w-3,y+h-1);
            g.drawLine(x+w-2,y+h-1,x+w-2,y+h-2);
            g.drawLine(x+w-1,y+h-2,x+w-1,y+h-3);
            break;
          case '�': // epsilon
            g.drawChar('e',x,y,g.TOP|g.LEFT);
            x += font.charWidth('e')*67/112;
            g.setColor(menuColor[menuStackPtr]);
            g.fillRect(x,y,w,h);
            g.setColor(0);
            w = 1;
            break;
          case '�': // theta
            g.drawChar('O',x,y,g.TOP|g.LEFT);
            g.drawChar('-',x,y-1,g.TOP|g.LEFT);
            g.drawChar('-',x+font.charWidth('O')-font.charWidth('-'),y-1,
                       g.TOP|g.LEFT);
            break;
        }
        x += w;
      }
      else {
        if (sub)
          g.drawChar(c,x,y+normalFont.getHeight()-
                     getBaselinePosition(smallFont),g.TOP|g.LEFT);
        else if (sup)
          g.drawChar(c,x,y-1,g.TOP|g.LEFT);
        else
          g.drawChar(c,x,y,g.TOP|g.LEFT);
        if (overline) {
          g.drawLine(x-1,y,x+font.charWidth(c)-1,y);
          if (bold)
            g.drawLine(x-1,y+1,x+font.charWidth(c)-1,y+1);
        }
        x += font.charWidth(c);
      }
    }
  }

  private void drawMenuItem(Graphics g, Menu menu, int x, int y, int anchor) {
    if (menu==null)
      return;
    boolean bold = menu.subMenu==null && (menu.flags&CmdDesc.SUBMENU_REQUIRED)==0;
    int width = labelWidth(menu.label,bold);
    if ((anchor & g.RIGHT) != 0)
      x -= width;
    else if ((anchor & g.HCENTER) != 0)
      x -= width/2;
    if ((anchor & g.BOTTOM) != 0)
      y -= menuFont.getHeight();
    drawLabel(g,menu.label,bold,x,y);
  }

  private void drawMenu(Graphics g) {
    int w = menuW;
    int h = menuH;
    int x = menuX;
    int y = menuY;
    int ym = ((y+h-3)-menuFont.getHeight()+(y+3))/2;
    // Draw menu title
    g.setColor(menuColor[menuStackPtr]/4);
    g.fillRect(x,y-menuFont.getHeight()-1,w/2,menuFont.getHeight()+1);
    g.setColor(menuColor[menuStackPtr]);
    g.setFont(menuFont);
    int titleStackPtr = menuStackPtr;
    while ((menuStack[titleStackPtr].flags & CmdDesc.TITLE_SKIP)!=0)
      titleStackPtr--;
    String label = menuStack[titleStackPtr].label;
    drawLabel(g,label,false,x+2,y-menuFont.getHeight());
    // Draw 3D menu background
    g.fillRect(x+2,y+2,w-4,h-4);
    g.setColor((menuColor[menuStackPtr]+0xfcfcfc)/2);
    g.fillRect(x,y+1,2,h-1);
    g.setColor(menuColor[menuStackPtr]/2);
    g.fillRect(x+w-2,y+1,2,h-1);
    g.setColor((menuColor[menuStackPtr]+0xfcfcfc)/2);
    g.fillRect(x,y,w,1);
    g.fillRect(x+1,y+1,w-2,1);
    g.setColor(menuColor[menuStackPtr]/2);
    g.fillRect(x+2,y+h-2,w-4,1);
    g.fillRect(x+1,y+h-1,w-2,1);
    // Draw menu items
    g.setColor(0);
    Menu [] subMenu = menuStack[menuStackPtr].subMenu;
    if (subMenu.length>=1)
      drawMenuItem(g,subMenu[0],x+w/2,y+3,g.TOP|g.HCENTER);
    if (subMenu.length>=2)
      drawMenuItem(g,subMenu[1],x+3,ym,g.TOP|g.LEFT);
    if (subMenu.length>=3)
      drawMenuItem(g,subMenu[2],x+w-3,ym,g.TOP|g.RIGHT);
    if (subMenu.length>=4)
      drawMenuItem(g,subMenu[3],x+w/2,y+h-3,g.BOTTOM|g.HCENTER);
    if (subMenu.length>=5 && subMenu[4]!=null)
      drawMenuItem(g,subMenu[4],x+w/2,ym,
                   g.TOP|g.HCENTER);
    else {
      // Draw a small "joystick" in the center
      y += h/2;
      x += w/2;
      g.setColor(menuColor[menuStackPtr]/4*3);
      g.fillRect(x-1,y-10,3,21);
      g.fillRect(x-10,y-1,21,3);
      g.fillArc(x-5,y-5,11,11,0,360);
    }
  }

  private void drawNumber(Graphics g, int i, boolean cleared, int offY,
                          int nLines) {
    numberFont.setMonospaced(true);
    if (i==0 && calc.inputInProgress) {
      StringBuffer tmp = calc.inputBuf;
      tmp.append('_');
      if (tmp.length()>nDigits)
        numberFont.drawString(g,offX,offY+(nLines-1)*numberHeight,tmp,
                              tmp.length()-nDigits);
      else {
        numberFont.drawString(g,offX,offY+(nLines-1)*numberHeight,tmp,0);
        if (!cleared) {
          g.setColor(0);
          g.fillRect(offX+tmp.length()*numberWidth,
                     offY+(nLines-1)*numberHeight,
                     (nDigits-tmp.length())*numberWidth,numberHeight);
        }
      }
      tmp.setLength(tmp.length()-1);
    } else {
      String tmp = calc.getStackElement(i);
      if (tmp.length()>nDigits)
        tmp = "*****";
      numberFont.drawString(
        g,offX+(nDigits-tmp.length())*numberWidth,
        offY+(nLines-1-i)*numberHeight,tmp);
      if (!cleared) {
        g.setColor(0);
        g.fillRect(offX,offY+(nLines-1-i)*numberHeight,
                   (nDigits-tmp.length())*numberWidth,numberHeight);
      }
    }
  }

  private void drawMonitor(Graphics g, int i, boolean cleared) {
    String label = calc.getMonitorLabel(i);
    String lead = calc.getMonitorLead(i);
    String element = calc.getMonitorElement(i);
    if (element.length()+label.length()+lead.length()>nDigits)
      element = "*****";
    numberFont.setMonospaced(true);
    numberFont.drawString(
      g,offX+(nDigits-element.length())*numberWidth,
      offYMonitor+i*numberHeight,element);
    numberFont.drawString(g,offX,offYMonitor+i*numberHeight,label);
    numberFont.drawString(g,offX+numberFont.stringWidth(label),
                          offYMonitor+i*numberHeight,lead);
    if (!cleared) {
      g.setColor(0);
      g.fillRect(offX+(label.length()+lead.length())*numberWidth,
                 offYMonitor+i*numberHeight,
                 (nDigits-element.length()-label.length()-lead.length())*
                 numberWidth, numberHeight);
    }
  }

  public void paint(Graphics g) {
    boolean cleared = false;
    int i;
    String message;

    message = calc.getMessage();
    if (message != null) {
      midlet.displayMessage(calc.getMessageCaption(), message);
      return;
    }
    
    if (numRepaintLines == 100 || !internalRepaint) {
      clearScreen(g);
      cleared = true;
      numRepaintLines = 100;
    }
    internalRepaint = false;

    if (numRepaintLines >= 0) {
      if (menuStackPtr < 0 || cleared) {
        if (numRepaintLines > 16)
          numRepaintLines = 16;
        int monitorSize = calc.getMonitorSize();
        if (monitorSize > 0) {
          if (numRepaintLines > nLinesMonitor-monitorSize)
            numRepaintLines = nLinesMonitor-monitorSize;

          for (i=0; i<numRepaintLines && i<16; i++)
            drawNumber(g,i,cleared,offY2,nLinesMonitor);
          if (cleared)
            for (i=0; i<monitorSize; i++)
              drawMonitor(g,i,cleared);
          g.setColor(255,255,255);
          g.drawLine(0,offYMonitor+monitorSize*numberHeight+1,
                     getWidth(),offYMonitor+monitorSize*numberHeight+1);
        } else {
          if (numRepaintLines > nLines)
            numRepaintLines = nLines;
          for (i=0; i<numRepaintLines; i++)
            drawNumber(g,i,cleared,offY,nLines);
        }
      }
      if (menuStackPtr >= 0)
        drawMenu(g);
    }
    numRepaintLines = -1;
  }

  private void checkRepaint() {
    int repaintLines = calc.numRepaintLines();
    if (repaintLines == 0)
      repaintLines = -1; // Because "0" repaints menu
    if (repaintLines > numRepaintLines)
      numRepaintLines = repaintLines;
    if (numRepaintLines >= 0) {
      internalRepaint = true;
      repaint();
    }
  }

  private void clearKeyPressed() throws OutOfMemoryError {
    if (calc.isInsideMonitor) {
      calc.command(CalcEngine.MONITOR_EXIT,0);
      setCommands("ENTER","+");
      return;
    } else if (menuStackPtr >= 0) {
      menuStackPtr--;
      if (menuStackPtr >= 0)
        numRepaintLines = 0; // Force repaint of menu
      else
        numRepaintLines = 100; // Force repaint of all
      return;
    }
    menuStackPtr = -1; // In case it was -2, which signals no-repeat
    calc.command(CalcEngine.CLEAR,0);
  }

  private void clearKeyRepeated() throws OutOfMemoryError {
    if (calc.isInsideMonitor) {
      menuStackPtr = -2; // should not continue by clearing the input...
    } else if (menuStackPtr >= 0) {
      menuStackPtr = -2; // should not continue by clearing the input...
      numRepaintLines = 100; // Force repaint of all
    } else {
      if (!calc.inputInProgress || menuStackPtr == -2)
        return;
      calc.command(CalcEngine.CLEAR,0);
    }
  }

  private void setFullScreen(boolean fs) {
    fullScreen = fs;
    setFullScreenMode(fullScreen);
  }

  private void menuAction(int menuIndex) throws OutOfMemoryError {
    boolean graph=false;
    int graphParam=0;

    if (calc.isInsideMonitor) {
      switch (menuIndex) {
        case 0: calc.command(CalcEngine.MONITOR_UP   ,0); break;
        case 3: calc.command(CalcEngine.MONITOR_DOWN ,0); break;
        case 1: calc.command(CalcEngine.MONITOR_LEFT ,0); break;
        case 2: calc.command(CalcEngine.MONITOR_RIGHT,0); break;
        case 4:
          calc.command(CalcEngine.MONITOR_PUSH ,0);
          setCommands("ENTER","+");
          break;
      }
      return;
    }
    
    if (menuStackPtr < 0) {
      // On entering the menu, switch math/trig menus with bit-op
      // menus if not base-10
      if (calc.format.base == 10) {
        menu.subMenu[1] = math;
        menu.subMenu[2] = trig;
        // Also switch coord menu with cplx menu if x or y are complex
        if (calc.hasComplexArgs()) {
          trig.subMenu[4].subMenu[3] = cplxMenu;
        } else {
          trig.subMenu[4].subMenu[3] = coordMenu;
        }
      } else {
        menu.subMenu[1] = bitOp;
        menu.subMenu[2] = bitMath;
      }
      // Switch between prog1 and prog2 if recording a program
      // and between monitor off and prog monitor
      if (calc.progRecording) {
        menu.subMenu[4].subMenu[1] = prog2;
        menu.subMenu[4].subMenu[3].subMenu[4] = monitorProgMenu;
        // Cannot use NO_PROG commands during program recording
        if (repeatedMenuItem!=null &&
            (repeatedMenuItem.flags & CmdDesc.NO_PROG)!=0)
          repeatedMenuItem = null;
      } else {
        menu.subMenu[4].subMenu[1] = prog1;        
        menu.subMenu[4].subMenu[3].subMenu[4] = monitorOffMenu;
      }
      // Change basicMenu[4] to enterMonitor if monitoring or repeated item
      if (calc.getActualMonitorSize() > 0)
        basicMenu.subMenu[4] = enterMonitor;
      else
        basicMenu.subMenu[4] = repeatedMenuItem;
    }

    if (menuStackPtr < 0 && menuIndex < 4) {
      // Go directly to submenu
      menuStack[0] = menu;
      menuStack[1] = menu.subMenu[menuIndex];
      menuStackPtr = 1;
      numRepaintLines = 0; // Force repaint of menu
    } else if (menuStackPtr < 0) {
      // Open top level menu
      menuStack[0] = menu;
      menuStackPtr = 0;
      numRepaintLines = 0; // Force repaint of menu
    } else if (menuIndex < menuStack[menuStackPtr].subMenu.length) {
      Menu subItem = menuStack[menuStackPtr].subMenu[menuIndex];
      if (subItem == null) {
        ; // NOP
      } else if (subItem.subMenu != null) {
        // Open submenu
        menuStackPtr++;
        menuStack[menuStackPtr] = subItem;
        // Set correct labels
        if (subItem == progMenu.subMenu[4])
          for (int i=0; i<5; i++)
            progMenu.subMenu[4].subMenu[i].label = calc.progLabels[i+4];
        numRepaintLines = 0; // Force repaint of menu
      } else if ((subItem.flags & CmdDesc.SUBMENU_REQUIRED)!=0) {
        // Open number/finance/program submenu
        Menu sub =
          (subItem.flags & CmdDesc.NUMBER_REQUIRED )!=0 ? numberMenu :
          (subItem.flags & CmdDesc.FINANCE_REQUIRED)!=0 ? financeMenu :
          progMenu;
        // Set correct labels
        if (sub == progMenu)
          for (int i=0; i<4; i++)
            progMenu.subMenu[i].label = calc.progLabels[i];
        menuCommand = subItem.command; // Save current command
        menuItem = subItem;
        sub.label = subItem.label; // Set correct label
        sub.flags = subItem.flags; // Set correct flags
        menuStackPtr++;
        menuStack[menuStackPtr] = sub;
        numRepaintLines = 0; // Force repaint of menu
      } else {
        int command = subItem.command;
        if (command == EXIT) {
          // Internal exit command
          midlet.exitRequested();
        } else if (command == RESET) {
          midlet.resetRequested();
        } else if (command == FULLSCREEN) {
          setFullScreen(!fullScreen);
        } else if (command >= FONT_SMALL && command <= FONT_XXXLARGE) {
          // Internal font command
          setNumberFont(command-FONT_SMALL);
        } else if (command >= NUMBER_0 && command <= NUMBER_15) {
          // Number has been entered for previous command
          if (menuCommand >= CalcEngine.PROG_DRAW &&
              menuCommand <= CalcEngine.PROG_MINMAX) {
            graph = true;
            graphParam = command-NUMBER_0;
          } if (menuCommand == CalcEngine.PROG_NEW) {
            int n = command-NUMBER_0;
            String name = calc.progLabels[n]==calc.emptyProg ? "" :
              calc.progLabels[n];
            midlet.askNewProgram(name,n);
          } else {
            calc.command(menuCommand,command-NUMBER_0);
          }
        } else if (command >= CalcEngine.AVG_DRAW &&
                   command <= CalcEngine.POW_DRAW) {
          menuCommand = command;
          graph = true;
        } else {
          // Normal calculator command
          if (command == CalcEngine.MONITOR_ENTER)
            if (calc.monitorMode == CalcEngine.MONITOR_PROG) // Different commands inside monitor 
              setCommands("SST","DEL");
            else
              setCommands("STO","RCL"); 
          calc.command(command,0);
        }

        if ((subItem.flags & CmdDesc.NO_REPEAT)==0) {
          // Repeat this command or parent in basicMenu[4]
          Menu item = subItem;
          while ((item.flags & CmdDesc.REPEAT_PARENT)!=0)
            item = menuStack[menuStackPtr--];
          if ((item.flags & CmdDesc.SUBMENU_REQUIRED)!=0)
            item = menuItem; // Switch from submenu to actual menu item
          if ((item.flags & CmdDesc.NO_REPEAT)==0)
            repeatedMenuItem = item;
        }

        menuStackPtr = -1;     // Remove menu
        numRepaintLines = 100; // Force repaint of all
      }
    }

    if (graph && calc.prepareGraph(menuCommand,graphParam)) {
      evenFrame = true;
      midlet.displayGraph(0,header-1,getWidth(),
                          getHeight()-header+1);
      numRepaintLines = 100;
    }
  }

  protected void keyPressed(int key) {
    try {
    repeating = false;
    int menuIndex = -1;
    switch (key) {
      case '0': case '1': case '2': case '3': case '4':
      case '5': case '6': case '7': case '8': case '9':
        if (menuStackPtr >= 0 || calc.isInsideMonitor) {
          if ((menuStackPtr>=0 && menuStack[menuStackPtr  ] == numberMenu) ||
              (menuStackPtr>=1 && menuStack[menuStackPtr-1] == numberMenu))
          {
            calc.command(menuCommand,key-'0');

            // Repeat menu or parent in basicMenu[4]
            Menu item = menuStack[menuStackPtr--];
            while ((item.flags & CmdDesc.REPEAT_PARENT)!=0)
              item = menuStack[menuStackPtr--];
            if ((item.flags & CmdDesc.SUBMENU_REQUIRED)!=0)
              item = menuItem; // Switch from submenu to actual menu item
            if ((item.flags & CmdDesc.NO_REPEAT)==0)
              repeatedMenuItem = item;

            menuStackPtr = -1;
            numRepaintLines = 100; // Force repaint of all
            break;
          }
          switch (getGameAction(key)) {
            case UP:    menuIndex = 0; break;
            case DOWN:  menuIndex = 3; break;
            case LEFT:  menuIndex = 1; break;
            case RIGHT: menuIndex = 2; break;
            case FIRE:  menuIndex = 4; break;
            default:
              switch (key) {
                case '2': menuIndex = 0; break; // UP
                case '8': menuIndex = 3; break; // DOWN
                case '4': menuIndex = 1; break; // LEFT
                case '6': menuIndex = 2; break; // RIGHT
                case '5': menuIndex = 4; break; // PUSH
              }
              break;
          }
          break;
        }
        calc.command(CalcEngine.DIGIT_0+key-'0',0);
        break;
      case KEY_SEND:
        if (midlet.hasClearKey)
          clearKeyPressed();
        else
          menuIndex =  4;
        break;
      case '\b': case KEY_END:
        clearKeyPressed();
        break;
      case '#':
        if (!midlet.hasClearKey || menuStackPtr>=0 || calc.isInsideMonitor) {
          clearKeyPressed();
          break;
        }
        // deliberate fall-through
      case '-': case 'e': case 'E':
        if (menuStackPtr >= 0)
          return;
        calc.command(CalcEngine.SIGN_E,0);
        break;
      case '*': case '.': case ',': case 65452:
        if (menuStackPtr >= 0)
          return;
        if (!midlet.hasClearKey)
          calc.command(CalcEngine.SIGN_POINT_E,0);
        else
          calc.command(CalcEngine.DEC_POINT,0);
        break;
      case '\n': case '\r':
      case KEY_SOFTKEY1:
        commandAction(enter,this);
        return;
      case '+':
      case KEY_SOFTKEY2:
        commandAction(add,this);
        return;
      default:
        switch (getGameAction(key)) {
          case UP:    menuIndex = 0; break;
          case DOWN:  menuIndex = 3; break;
          case LEFT:  menuIndex = 1; break;
          case RIGHT: menuIndex = 2; break;
          case FIRE:  menuIndex = 4; break;
          case GAME_A: case GAME_B: case GAME_C: case GAME_D:
            // I have no idea how these keys are mapped, I just hope
            // one of them is mapped to something that we can use as
            // a "clear" key
            clearKeyPressed();
            break;
          default:
            // Nokia and other direct key mappings
            switch (key) {
              case KEY_UP_ARROW:    menuIndex = 0; break; // UP
              case KEY_DOWN_ARROW:  menuIndex = 3; break; // DOWN
              case KEY_LEFT_ARROW:  menuIndex = 1; break; // LEFT
              case KEY_RIGHT_ARROW: menuIndex = 2; break; // RIGHT
              case KEY_SOFTKEY3:    menuIndex = 4; break; // PUSH
              case -8:  // SonyEricsson "c"
              case -23: // Motorola "menu"
                clearKeyPressed();
                break;
              default:
                // Clear key could be mapped as something else...
                if (midlet.doubleKeyEvents)
                  // We don't yet know if we can treat this as "clear"
                  unknownKeyPressed = true;
                else
                  clearKeyPressed();
                break;
            }
            break;
        }
        break;
    }
    if (menuIndex >= 0)
      menuAction(menuIndex);

    checkRepaint();
    } catch (OutOfMemoryError e) {
      menuStackPtr = -1;     // Remove menu
      numRepaintLines = 100; // Force repaint of all
      midlet.outOfMemory();
    }
  }

  protected void keyRepeated(int key) {
    try {
    if (unknownKeyPressed) {
      // Can't repeat "delayed clear key"
      return;
    }
    switch (key) {
      case '1': case '2': case '3': case '4': case '5': case '6':
        if (repeating || menuStackPtr >= 0)
          return;
        calc.command(CalcEngine.DIGIT_A+key-'1',0);
        break;
      case KEY_SEND:
        if (midlet.hasClearKey)
          clearKeyRepeated();
        break;
      case '\b': case KEY_END:
        clearKeyRepeated();
        break;
      case '#':
        if (!midlet.hasClearKey)
          clearKeyRepeated();
        break;
      case '0': case '7': case '8': case '9':
      case '*':
        // Do nothing, but do not fall into the "default" below
        break;
      default:
        switch (getGameAction(key)) {
          case GAME_A: case GAME_B: case GAME_C: case GAME_D:
            clearKeyRepeated();
            break;
          default:
            if (key == -8 || key == -23)
              clearKeyRepeated();
            break;
        }
        break;
    }
    repeating = true;
    checkRepaint();
    } catch (OutOfMemoryError e) {
      menuStackPtr = -1;     // Remove menu
      numRepaintLines = 100; // Force repaint of all
      midlet.outOfMemory();
    }
  }

  protected void keyReleased(int key) {
    try {
    if (unknownKeyPressed) {
      // It's a "delayed clear key"
      unknownKeyPressed = false;
      clearKeyPressed();
      checkRepaint();
    }
    } catch (OutOfMemoryError e) {
      menuStackPtr = -1;     // Remove menu
      numRepaintLines = 100; // Force repaint of all
      midlet.outOfMemory();
    }
  }

  protected void pointerPressed(int x, int y) {
    try {
    int menuIndex, q=0;

    if (!automaticCommands() && y >= getHeight()-footer) {
      if (x < getWidth()/2)
        commandAction(enter,this);
      else
        commandAction(add,this);
      return;
    }

    x = x-menuX-menuW/2; if (x<0) { x = -x; q += 1; }
    y = y-menuY-menuH/2; if (y<0) { y = -y; q += 2; }

    if (x*6 < menuW && y*6 < menuH) {
      menuIndex = 4;
    } else if ((x-y)*6 > menuW-menuH) {
      menuIndex = (q&1)==0 ? 2/*RIGHT*/: 1/*LEFT*/;
    } else {
      menuIndex = (q&2)==0 ? 3/*DOWN*/ : 0/*UP*/;
    }
    menuAction(menuIndex);
    checkRepaint();
    } catch (OutOfMemoryError e) {
      menuStackPtr = -1;     // Remove menu
      numRepaintLines = 100; // Force repaint of all
      midlet.outOfMemory();
    }
  }

  public void commandAction(Command c, Displayable d)
  {
    try {
    // If an unknown key has beed pressed but not released, ignore it
    unknownKeyPressed = false;

    // Nothing happens inside menu
    if (menuStackPtr >= 0)
      return;

    if (c == enter) {
      if (calc.isInsideMonitor) {
        calc.command(CalcEngine.MONITOR_PUT,0);
      } else {
        calc.command(CalcEngine.ENTER,0);
      }
    } else if (c == add) {
      if (calc.isInsideMonitor) {
        calc.command(CalcEngine.MONITOR_GET,0);
      } else {
        calc.command(CalcEngine.ADD,0);
      }
    }
    checkRepaint();
    } catch (OutOfMemoryError e) {
      menuStackPtr = -1;     // Remove menu
      numRepaintLines = 100; // Force repaint of all
      midlet.outOfMemory();
    }
  }

}
