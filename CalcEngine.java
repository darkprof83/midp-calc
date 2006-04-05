package ral;

import javax.microedition.lcdui.*;
import java.io.*;

public final class CalcEngine
{
  // Commands == "keystrokes"
  // Preserve these constants for programs to work from one version to the next
  public static final int DIGIT_0        =   0;
  public static final int DIGIT_1        =   1;
  public static final int DIGIT_2        =   2;
  public static final int DIGIT_3        =   3;
  public static final int DIGIT_4        =   4;
  public static final int DIGIT_5        =   5;
  public static final int DIGIT_6        =   6;
  public static final int DIGIT_7        =   7;
  public static final int DIGIT_8        =   8;
  public static final int DIGIT_9        =   9;
  public static final int DIGIT_A        =  10;
  public static final int DIGIT_B        =  11;
  public static final int DIGIT_C        =  12;
  public static final int DIGIT_D        =  13;
  public static final int DIGIT_E        =  14;
  public static final int DIGIT_F        =  15;
  public static final int SIGN_E         =  16;
  public static final int DEC_POINT      =  17;
  public static final int ENTER          =  18;
  public static final int CLEAR          =  19;
  public static final int ADD            =  20;
  public static final int SUB            =  21;
  public static final int MUL            =  22;
  public static final int DIV            =  23;
  public static final int NEG            =  24;
  public static final int RECIP          =  25;
  public static final int SQR            =  26;
  public static final int SQRT           =  27;
  public static final int PERCENT        =  28;
  public static final int PERCENT_CHG    =  29;
  public static final int YPOWX          =  30;
  public static final int XRTY           =  31;
  public static final int LN             =  32;
  public static final int EXP            =  33;
  public static final int LOG10          =  34;
  public static final int EXP10          =  35;
  public static final int LOG2           =  36;
  public static final int EXP2           =  37;
  public static final int PYX            =  38;
  public static final int CYX            =  39;
  public static final int FACT           =  40;
  public static final int GAMMA          =  41;
  public static final int RP             =  42;
  public static final int PR             =  43;
  public static final int ATAN2          =  44;
  public static final int HYPOT          =  45;
  public static final int SIN            =  46;
  public static final int COS            =  47;
  public static final int TAN            =  48;
  public static final int ASIN           =  49;
  public static final int ACOS           =  50;
  public static final int ATAN           =  51;
  public static final int SINH           =  52;
  public static final int COSH           =  53;
  public static final int TANH           =  54;
  public static final int ASINH          =  55;
  public static final int ACOSH          =  56;
  public static final int ATANH          =  57;
  public static final int PI             =  58;
  public static final int AND            =  59;
  public static final int OR             =  60;
  public static final int XOR            =  61;
  public static final int BIC            =  62;
  public static final int NOT            =  63;
  public static final int YUPX           =  64;
  public static final int YDNX           =  65;
  public static final int XCHG           =  66;
  public static final int CLS            =  67;
  public static final int RCLST          =  68;
  public static final int LASTX          =  69;
  public static final int UNDO           =  70;
  public static final int ROUND          =  71;
  public static final int CEIL           =  72;
  public static final int FLOOR          =  73;
  public static final int TRUNC          =  74;
  public static final int FRAC           =  75;
  public static final int STO            =  76;
  public static final int STP            =  77;
  public static final int RCL            =  78;
  public static final int XCHGMEM        =  79;
  public static final int CLMEM          =  80;
  public static final int SUMPL          =  81;
  public static final int SUMMI          =  82;
  public static final int CLST           =  83;
  public static final int AVG            =  84;
  public static final int AVGXW          =  85;
  public static final int STDEV          =  86;
  public static final int PSTDEV         =  87;
  public static final int LIN_AB         =  88;
  public static final int LIN_YEST       =  89;
  public static final int LIN_XEST       =  90;
  public static final int LIN_R          =  91;
  public static final int LOG_AB         =  92;
  public static final int LOG_YEST       =  93;
  public static final int LOG_XEST       =  94;
  public static final int LOG_R          =  95;
  public static final int EXP_AB         =  96;
  public static final int EXP_YEST       =  97;
  public static final int EXP_XEST       =  98;
  public static final int EXP_R          =  99;
  public static final int POW_AB         = 100;
  public static final int POW_YEST       = 101;
  public static final int POW_XEST       = 102;
  public static final int POW_R          = 103;
  public static final int N              = 104;
  public static final int SUMX           = 105;
  public static final int SUMXX          = 106;
  public static final int SUMLNX         = 107;
  public static final int SUMLN2X        = 108;
  public static final int SUMY           = 109;
  public static final int SUMYY          = 110;
  public static final int SUMLNY         = 111;
  public static final int SUMLN2Y        = 112;
  public static final int SUMXY          = 113;
  public static final int SUMXLNY        = 114;
  public static final int SUMYLNX        = 115;
  public static final int SUMLNXLNY      = 116;
  public static final int NORM           = 117;
  public static final int FIX            = 118;
  public static final int SCI            = 119;
  public static final int ENG            = 120;
  public static final int POINT_DOT      = 121;
  public static final int POINT_COMMA    = 122;
  public static final int POINT_REMOVE   = 123;
  public static final int POINT_KEEP     = 124;
  public static final int THOUSAND_DOT   = 125;
  public static final int THOUSAND_SPACE = 126;
  public static final int THOUSAND_QUOTE = 127;
  public static final int THOUSAND_NONE  = 128;
  public static final int BASE_BIN       = 129;
  public static final int BASE_OCT       = 130;
  public static final int BASE_DEC       = 131;
  public static final int BASE_HEX       = 132;
  public static final int TRIG_DEGRAD    = 133;
  public static final int TO_DEG         = 134;
  public static final int TO_RAD         = 135;
  public static final int RANDOM         = 136;
  public static final int TO_DHMS        = 137;
  public static final int TO_H           = 138;
  public static final int DHMS_PLUS      = 139;
  public static final int TIME           = 140;
  public static final int DATE           = 141;
  public static final int FACTORIZE      = 142;
  public static final int FINANCE_STO    = 143;
  public static final int FINANCE_RCL    = 144;
  public static final int FINANCE_SOLVE  = 145;
  public static final int FINANCE_CLEAR  = 146;
  public static final int FINANCE_BGNEND = 147;
  public static final int FINANCE_MULINT = 148;
  public static final int FINANCE_DIVINT = 149;
  // 150-153 previously occupied by MONITOR_*
  public static final int SIGN_POINT_E   = 154;
  public static final int TO_CPLX        = 155;
  public static final int CPLX_SPLIT     = 156;
  public static final int ABS            = 157;
  public static final int CPLX_ARG       = 158;
  public static final int CPLX_CONJ      = 159;
  public static final int ERFC           = 160;
  public static final int MOD            = 161;
  public static final int DIVF           = 162;
  public static final int XCHGST         = 163;
  public static final int ROLLDN         = 164;
  public static final int ROLLUP         = 165;
  public static final int CONST_c        = 166;
  public static final int CONST_h        = 167;
  public static final int CONST_mu_0     = 168;
  public static final int CONST_eps_0    = 169;
  public static final int CONST_NA       = 170;
  public static final int CONST_R        = 171;
  public static final int CONST_k        = 172;
  public static final int CONST_F        = 173;
  public static final int CONST_alpha    = 174;
  public static final int CONST_a_0      = 175;
  public static final int CONST_R_inf    = 176;
  public static final int CONST_mu_B     = 177;
  public static final int CONST_e        = 178;
  public static final int CONST_m_e      = 179;
  public static final int CONST_m_p      = 180;
  public static final int CONST_m_n      = 181;
  public static final int CONST_m_u      = 182;
  public static final int CONST_G        = 183;
  public static final int CONST_g_n      = 184;
  public static final int CONST_ly       = 185;
  public static final int CONST_AU       = 186;
  public static final int CONST_pc       = 187;
  public static final int CONST_km_mi    = 188;
  public static final int CONST_m_ft     = 189;
  public static final int CONST_cm_in    = 190;
  public static final int CONST_km_nm    = 191;
  public static final int CONST_m_yd     = 192;
  public static final int CONST_g_oz     = 193;
  public static final int CONST_kg_lb    = 194;
  public static final int CONST_mg_gr    = 195;
  public static final int CONST_kg_ton   = 196;
  public static final int CONST_J_cal    = 197;
  public static final int CONST_J_Btu    = 198;
  public static final int CONST_W_hp     = 199;
  public static final int CONST_l_pt     = 200;
  public static final int CONST_l_cup    = 201;
  public static final int CONST_l_gal    = 202;
  public static final int CONST_ml_floz  = 203;
  public static final int CONST_K_C      = 204;
  public static final int CONV_C_F       = 205;
  public static final int CONV_F_C       = 206;
  public static final int IF_EQUAL       = 207;
  public static final int IF_NEQUAL      = 208;
  public static final int IF_LESS        = 209;
  public static final int IF_LEQUAL      = 210;
  public static final int IF_GREATER     = 211;
  public static final int MIN            = 212;
  public static final int MAX            = 213;
  public static final int SELECT         = 214;
  public static final int RCL_X          = 215;
  public static final int STO_X          = 216;
  public static final int STP_X          = 217;
  public static final int TIME_NOW       = 218;
  public static final int DHMS_TO_UNIX   = 219;
  public static final int UNIX_TO_DHMS   = 220;
  public static final int DHMS_TO_JD     = 221;
  public static final int JD_TO_DHMS     = 222;
  public static final int DHMS_TO_MJD    = 223;
  public static final int MJD_TO_DHMS    = 224;
  public static final int SGN            = 225;
  public static final int PUSH_ZERO      = 226;
  public static final int PUSH_ZERO_N    = 227;
  public static final int PUSH_INF       = 228;
  public static final int PUSH_INF_N     = 229;
  public static final int PROG_EDIT      = 230;
  public static final int PROG_FINISH    = 231;
  public static final int PROG_RUN       = 232;
  public static final int PROG_PURGE     = 233;
  public static final int PROG_CLEAR     = 234;
  public static final int PROG_DIFF      = 235;
  public static final int TRANSP         = 236;
  public static final int DETERM         = 237;
  public static final int TRACE          = 238;
  public static final int MATRIX_NEW     = 239;
  public static final int MATRIX_CONCAT  = 240;
  public static final int MATRIX_STACK   = 241;
  public static final int MATRIX_SPLIT   = 242;
  public static final int MONITOR_NONE   = 243;
  public static final int MONITOR_MEM    = 244;
  public static final int MONITOR_STAT   = 245;
  public static final int MONITOR_FINANCE= 246;
  public static final int MONITOR_MATRIX = 247;
  public static final int MONITOR_ENTER  = 248;
  public static final int MONITOR_EXIT   = 249;
  public static final int MONITOR_UP     = 250;
  public static final int MONITOR_DOWN   = 251;
  public static final int MONITOR_LEFT   = 252;
  public static final int MONITOR_RIGHT  = 253;
  public static final int MONITOR_PUSH   = 254;
  public static final int MONITOR_PUT    = 255;
  public static final int MONITOR_GET    = 256;
  public static final int STAT_RCL       = 257;
  public static final int STAT_STO       = 258;
  public static final int MATRIX_SIZE    = 259;
  public static final int MATRIX_AIJ     = 260;
  public static final int TRANSP_CONJ    = 261;
  public static final int GUESS          = 262;
  public static final int INVERFC        = 263;
  public static final int PHI            = 264;
  public static final int INVPHI         = 265;
  public static final int MONITOR_PROG   = 266;

  public static final int MATRIX_STO     = 512; // Special bit pattern
  public static final int MATRIX_RCL     = 768; // Special bit pattern

  // These commands are handled from CalcCanvas
  public static final int AVG_DRAW       = 300;
  public static final int LIN_DRAW       = 301;
  public static final int LOG_DRAW       = 302;
  public static final int EXP_DRAW       = 303;
  public static final int POW_DRAW       = 304;
  public static final int PROG_DRAW      = 305;
  public static final int PROG_DRAWPOL   = 306;
  public static final int PROG_DRAWPARM  = 307;
  public static final int PROG_SOLVE     = 308;
  public static final int PROG_INTEGR    = 309;
  public static final int PROG_MINMAX    = 310;

  // Special commands
  public static final int FINALIZE       = 400;
  public static final int FREE_MEM       = 401;

  private static final int STACK_SIZE    = 16;
  private static final int MEM_SIZE      = 16;
  private static final int STAT_SIZE     = 13;
  private static final int STATLOG_SIZE  = 64;
  private static final int FINANCE_SIZE  = 5;
  private static final int NUM_PROGS     = 9;

  private static final String empty = "";

  public Real [] stack;
  public Real [] stackI;
  public String [] stackStr;
  public Real lastx,lasty,lastz;
  public Real lastxi,lastyi,lastzi;

  public Real [] mem;
  public Real [] memI;

  public Real SUM1,SUMx,SUMx2,SUMy,SUMy2,SUMxy;
  public Real SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMxlny,SUMylnx,SUMlnxlny;
  public Real [] stat;
  public int [] statLog; // Low-precision statistics log
  public int statLogStart,statLogSize;

  public Real PV,FV,NP,PMT,IR;
  public Real [] finance;
  public boolean begin;

  public Real.NumberFormat format;
  public boolean degrees;
  public StringBuffer inputBuf;
  public boolean inputInProgress;
  private Real rTmp,rTmp2,rTmp3,rTmp4;
  private int repaintLines;
  public boolean initialized = false;
  private String message, messageCaption;

  public Matrix[] matrixCache;

  public int monitorMode;
  public int monitorSize,initialMonitorSize;
  public int maxMonitorSize,maxMonitorDisplaySize;
  public int monitorX,monitorY;
  public int monitorYOff;
  public boolean isInsideMonitor;
  public Real [] monitors;
  public Real [] imagMonitors;
  public Matrix monitoredMatrix;
  public String monitorCaption;
  public String [] monitorStr;
  public String [] monitorLabels;
  private static final String [] statLabels =
    { "n","�x","�x�","�y","�y�","�xy","ߣx","ߣ�x",
      "ߣy","ߣ�y","�x�y","�y�x","ߣx�y" };
  private static final String [] financeLabels =
    { "pv","fv","np","pmt","ir%" };
  private String [] memLabels;
  private String [] matrixLabels;

  private static final int UNDO_NONE   = 0;
  private static final int UNDO_UNARY  = 1;
  private static final int UNDO_BINARY = 2;
  private static final int UNDO_TRINARY= 3;
  private static final int UNDO_PUSH   = 4;
  private static final int UNDO_PUSH2  = 5;
  private static final int UNDO_XY     = 6;
  private static final int UNDO_PUSHXY = 7;
  private static final int UNDO_ROLLDN = 8;
  private static final int UNDO_ROLLUP = 9;
  private static final int UNDO_XCHGST =10;
  private int undoStackEmpty = 0;
  private int undoOp = UNDO_NONE;

  public boolean progRecording;
  public boolean progRunning;
  public static final int PROGLABEL_SIZE = 5;
  public static final String emptyProg = "< >";
  public String [] progLabels;
  private short [][] prog;
  private int progCounter;
  private int currentProg;

  private int progLineAddr [] = null;
  // for reopening monitor with same amount of lines by "edit" command
  public int progInitialMonitorSize = 0;

  private static Real[] allocRealArray(int n) {
    Real[] a = new Real[n];
    for (int i=0; i<n; i++)
      a[i] = new Real();
    return a;
  }

  private static void clearRealArray(Real [] a) {
    if (a != null)
      for (int i=0; i<a.length; i++)
        a[i] = null;
  }

  public CalcEngine()
  {
    format = new Real.NumberFormat();
    stack = allocRealArray(STACK_SIZE);
    stackI = allocRealArray(STACK_SIZE);
    stackStr = new String[STACK_SIZE];
    monitorStr = new String[MEM_SIZE];
    matrixCache = new Matrix[STACK_SIZE+MEM_SIZE+3];
    maxMonitorDisplaySize = 100; // For now, before an actual max is set

    lastx  = new Real();
    lasty  = new Real();
    lastz  = new Real();
    lastxi = new Real();
    lastyi = new Real();
    lastzi = new Real();
    rTmp   = new Real();
    rTmp2  = new Real();
    rTmp3  = new Real();
    rTmp4  = new Real();
    inputBuf = new StringBuffer(40);
    degrees = false;
    begin = false;
    clearStack();
    monitorMode = MONITOR_NONE;
    monitorSize = 0;
    clearMonitorStrings();
    memLabels = new String[MEM_SIZE];

    prog = new short[NUM_PROGS][];
    progLabels = new String[NUM_PROGS];
    for (int i=0; i<NUM_PROGS; i++)
      progLabels[i] = emptyProg;

    Real.accumulateRandomness(System.currentTimeMillis());
  }

  private void clearStrings() {
    for (int i=0; i<STACK_SIZE; i++)
      if (stackStr[i] != empty)
        stackStr[i] = null;
    clearMonitorStrings();
    repaintAll();
  }

  private void clearMonitorStrings() {
    for (int i=0; i<monitorStr.length; i++)
      monitorStr[i] = null;
    repaintAll();
  }

  private void clearStack() {
    inputInProgress = false;
    for (int i=0; i<STACK_SIZE; i++) {
      stack[i].makeZero();
      stackI[i].makeZero();
      stackStr[i] = empty;
    }
    repaintAll();
  }

  private void clearMem() {
    clearRealArray(mem);
    clearRealArray(memI);
    mem = null;
    memI = null;
  }

  private void clearStat() {
    SUM1      = null;
    SUMx      = null;
    SUMx2     = null;
    SUMy      = null;
    SUMy2     = null;
    SUMxy     = null;
    SUMlnx    = null;
    SUMln2x   = null;
    SUMlny    = null;
    SUMln2y   = null;
    SUMxlny   = null;
    SUMylnx   = null;
    SUMlnxlny = null;
    clearRealArray(stat);
    stat      = null;
    statLog   = null;
  }

  private void clearFinance() {
    PV  = null;
    FV  = null;
    NP  = null;
    PMT = null;
    IR  = null;
    clearRealArray(finance);
    finance = null;
  }

  private void tryClearModules(boolean clearMem, boolean clearFinance) {
    int i;
    if (clearMem && mem != null) {
      for (i=0; i<MEM_SIZE; i++)
        if (!mem[i].isZero() || !memI[i].isZero())
          break;
      if (i==MEM_SIZE)
        clearMem();
    }
    if (clearFinance && finance != null) {
      for (i=0; i<FINANCE_SIZE; i++)
        if (!finance[i].isZero())
          break;
      if (i==FINANCE_SIZE)
        clearFinance();
    }
  }

  private void allocMem() {
    if (mem != null)
      return;
    matrixGC();
    mem = allocRealArray(MEM_SIZE);
    memI = allocRealArray(MEM_SIZE);
    if (monitorMode == MONITOR_MEM) {
      monitors = mem;
      imagMonitors = memI;
    }
  }

  private void allocStat() {
    if (stat != null)
      return;
    matrixGC();
    stat = allocRealArray(STAT_SIZE);
    SUM1      = stat[ 0];
    SUMx      = stat[ 1];
    SUMx2     = stat[ 2];
    SUMy      = stat[ 3];
    SUMy2     = stat[ 4];
    SUMxy     = stat[ 5];
    SUMlnx    = stat[ 6];
    SUMln2x   = stat[ 7];
    SUMlny    = stat[ 8];
    SUMln2y   = stat[ 9];
    SUMxlny   = stat[10];
    SUMylnx   = stat[11];
    SUMlnxlny = stat[12];
    statLog = new int[STATLOG_SIZE*2];
    statLogStart = statLogSize = 0;
    if (monitorMode == MONITOR_STAT) {
      monitors = stat;
      imagMonitors = null;
    }
  }

  private void allocFinance() {
    if (finance != null)
      return;
    matrixGC();
    finance = allocRealArray(FINANCE_SIZE);
    PV  = finance[0];
    FV  = finance[1];
    NP  = finance[2];
    PMT = finance[3];
    IR  = finance[4];
    if (monitorMode == MONITOR_FINANCE) {
      monitors = finance;
      imagMonitors = null;
    }
  }

  private int getStackHeight() {
    int stackHeight;
    for (stackHeight=0; stackHeight<STACK_SIZE; stackHeight++)
      if (stackStr[stackHeight] == empty)
        break;
    return stackHeight;
  }

  public void saveState(DataOutputStream out) throws IOException
  {
    tryClearModules(true,true);
    lasty.makeZero(); // Clear this in case it is a Matrix reference
    lastz.makeZero(); // ...and this
    matrixGC();

    if (monitorMode == MONITOR_PROG)
      monitorMode = MONITOR_NONE;

    int i,j;
    byte [] realBuf = new byte[12];

    // Stack
    int stackHeight = getStackHeight();
    if (stackHeight > 0) {
      out.writeShort(STACK_SIZE*12*2);
      for (i=0; i<STACK_SIZE; i++) {
        stack[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
      for (i=0; i<STACK_SIZE; i++) {
        stackI[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
    } else {
      out.writeShort(0);
    }

    //Memory
    if (mem != null) {
      out.writeShort(MEM_SIZE*12*2);
      for (i=0; i<MEM_SIZE; i++) {
        mem[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
      for (i=0; i<MEM_SIZE; i++) {
        memI[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
    } else {
      out.writeShort(0);
    }

    // Statistics
    if (stat != null) {
      out.writeShort(STAT_SIZE*12+STATLOG_SIZE*2*4+2);
      for (i=0; i<STAT_SIZE; i++) {
        stat[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
      for (i=0; i<STATLOG_SIZE*2; i++)
        out.writeInt(statLog[i]);
      out.writeByte(statLogStart);
      out.writeByte(statLogSize);
    } else {
      out.writeShort(0);
    }

    // Finance
    if (finance != null) {
      out.writeShort(FINANCE_SIZE*12);
      for (i=0; i<FINANCE_SIZE; i++) {
        finance[i].toBytes(realBuf,0);
        out.write(realBuf);
      }
    } else {
      out.writeShort(0);
    }

    // Settings
    out.writeShort(10+8*2+12*2);
    out.writeByte(stackHeight);
    out.writeByte((degrees ? 1 : 0) + (begin ? 2 : 0));
    out.writeByte(format.base);
    out.writeByte(format.maxwidth);
    out.writeByte(format.precision);
    out.writeByte(format.fse);
    out.writeByte(format.point);
    out.writeBoolean(format.removePoint);
    out.writeByte(format.thousand);
    out.writeByte(((monitorMode-MONITOR_NONE)<<5) + monitorSize);
    out.writeLong(Real.randSeedA);
    out.writeLong(Real.randSeedB);
    lastx.toBytes(realBuf,0);
    out.write(realBuf);
    lastxi.toBytes(realBuf,0);
    out.write(realBuf);

    // Programs
    for (i=0; i<NUM_PROGS; i++) {
      if (prog[i]!=null) {
        out.writeShort(PROGLABEL_SIZE*2+prog[i].length*2);
        for (j=0; j<PROGLABEL_SIZE; j++)
          out.writeChar(j<progLabels[i].length() ? progLabels[i].charAt(j):0);
        for (j=0; j<prog[i].length; j++)
          out.writeShort(prog[i][j]);
      } else {
        out.writeShort(0);
      }
    }

    // Matrices
    for (i=0; i<matrixCache.length; i++) {
      if (matrixCache[i] != null) {
        Matrix M = matrixCache[i];
        if (M.rows<0xffff && M.cols<0xffff) {
          out.writeShort(2+2+M.rows*M.cols*(12+(M.DI!=null?12:0)));
          out.writeShort((short)M.rows);
          out.writeShort((short)M.cols);
          for (int c=0; c<M.cols; c++)
            for (int r=0; r<M.rows; r++) {
              M.getElement(r,c,rTmp,null);
              rTmp.toBytes(realBuf,0);
              out.write(realBuf);
            }
          if (M.DI != null)
            for (int c=0; c<M.cols; c++)
              for (int r=0; r<M.rows; r++) {
                M.getElement(r,c,null,rTmp);
                rTmp.toBytes(realBuf,0);
                out.write(realBuf);
              }
        }
        matrixCache[i] = null; // Free memory as we go
      } else {
        out.writeShort(0);
      }
    }
  }

  public void restoreState(DataInputStream in) throws IOException {
    int length,i,j;
    byte [] realBuf = new byte[12];

    repaintAll(); // Better do it now in case of IOException

    // Stack
    length = in.readShort();
    if (length >= STACK_SIZE*12) {
      for (i=0; i<STACK_SIZE; i++) {
        in.readFully(realBuf);
        stack[i].assign(realBuf,0);
      }
      length -= STACK_SIZE*12;
      if (length >= STACK_SIZE*12) {
        for (i=0; i<STACK_SIZE; i++) {
          in.readFully(realBuf);
          stackI[i].assign(realBuf,0);
        }
        length -= STACK_SIZE*12;
      }
    }
    in.skip(length);

    // Memory
    length = in.readShort();
    if (length >= MEM_SIZE*12) {
      allocMem();
      for (i=0; i<MEM_SIZE; i++) {
        in.readFully(realBuf);
        mem[i].assign(realBuf,0);
      }
      length -= MEM_SIZE*12;
      if (length >= MEM_SIZE*12) {
        for (i=0; i<MEM_SIZE; i++) {
          in.readFully(realBuf);
          memI[i].assign(realBuf,0);
        }
        length -= MEM_SIZE*12;
      }
    }
    in.skip(length);

    // Statistics
    length = in.readShort();
    if (length >= STAT_SIZE*12+STATLOG_SIZE*2*4+2) {
      allocStat();
      for (i=0; i<STAT_SIZE; i++) {
        in.readFully(realBuf);
        stat[i].assign(realBuf,0);
      }
      for (i=0; i<STATLOG_SIZE*2; i++)
        statLog[i] = in.readInt();
      statLogStart = in.readByte();
      statLogSize  = in.readByte();
      length -= STAT_SIZE*12+STATLOG_SIZE*2*4+2;
    }
    in.skip(length);

    // Finance
    length = in.readShort();
    if (length >= FINANCE_SIZE*12) {
      allocFinance();
      for (i=0; i<FINANCE_SIZE; i++) {
        in.readFully(realBuf);
        finance[i].assign(realBuf,0);
      }
      length -= FINANCE_SIZE*12;
    }
    in.skip(length);

    // Settings
    length = in.readShort();
    if (length >= 10+8*2+12) {
      int stackHeight = in.readByte();
      for (i=0; i<STACK_SIZE; i++)
        stackStr[i] = i<stackHeight ? null : empty;
      int flags = in.readByte();
      degrees            = (flags&1) != 0;
      begin              = (flags&2) != 0;
      format.base        = in.readByte();
      format.maxwidth    = in.readByte();
      format.precision   = in.readByte();
      format.fse         = in.readByte();
      format.point       = (char)in.readByte();
      format.removePoint = in.readBoolean();
      format.thousand    = (char)in.readByte();
      int monitor        = in.readByte();
      Real.randSeedA     = in.readLong();
      Real.randSeedB     = in.readLong();
      in.readFully(realBuf);
      lastx.assign(realBuf,0);

      monitorMode = MONITOR_NONE+((monitor>>5)&7);
      monitorSize = monitor&0x1f;
      if (monitorMode == MONITOR_MEM)
        setMonitoring(monitorMode,monitorSize,MEM_SIZE,mem,memI,memLabels);
      else if (monitorMode == MONITOR_STAT)
        setMonitoring(monitorMode,monitorSize,STAT_SIZE,stat,null,statLabels);
      else if (monitorMode == MONITOR_FINANCE)
        setMonitoring(monitorMode,FINANCE_SIZE,FINANCE_SIZE,finance,null,
                      financeLabels);
      else if (monitorMode == MONITOR_MATRIX) {
        setMonitoring(monitorMode,monitorSize,0,null,null,null);
        monitoredMatrix = null;
        // updateMatrixMonitor() will be run later
      }
      length -= 10+8*2+12;
    }
    if (length >= 12) {
      in.readFully(realBuf);
      lastxi.assign(realBuf,0);
      length -= 12;
    }
    in.skip(length);

    // Programs
    char [] label = new char[PROGLABEL_SIZE];
    for (i=0; i<NUM_PROGS; i++) {
      length = in.readShort();
      if (length >= PROGLABEL_SIZE*2) {
        int labelLen=0;
        for (j=0; j<PROGLABEL_SIZE; j++) {
          label[j] = in.readChar();
          if (label[j] != 0)
            labelLen = j+1;
        }
        progLabels[i] = new String(label,0,labelLen);
        prog[i] = new short[(length-PROGLABEL_SIZE*2)/2];
        for (j=0; j<prog[i].length; j++)
          prog[i][j] = in.readShort();
        length -= PROGLABEL_SIZE*2+prog[i].length*2;
      }
      in.skip(length);
    }

    // Matrices
    for (i=0; i<matrixCache.length; i++) {
      length = in.readShort();
      if (length >= 2+2) {
        int rows = in.readShort()&0xffff;
        int cols = in.readShort()&0xffff;
        length -= 2*2;
        if (length >= rows*cols*12) {
          Matrix M = new Matrix(rows,cols);
          for (int c=0; c<cols; c++)
            for (int r=0; r<rows; r++) {
              in.readFully(realBuf);
              rTmp.assign(realBuf,0);
              M.setElement(r,c,rTmp,null);
            }
          length -= rows*cols*12;
          if (length >= rows*cols*12) {
            for (int c=0; c<cols; c++)
              for (int r=0; r<rows; r++) {
                in.read(realBuf);
                rTmp.assign(realBuf,0);
                M.setElement(r,c,null,rTmp);
              }
            length -= rows*cols*12;
          }
          matrixCache[i] = M;
        }
      }
      in.skip(length);
    }

    if (monitorMode == MONITOR_MATRIX)
      updateMatrixMonitor();
  }

  private Matrix getMatrix(Real x) {
    if (x==null || !x.isNan())
      return null;
    int ref;
    if ((ref = (int)x.mantissa-1) < 0)
      return null;
    if (matrixCache[ref] == null || Matrix.isInvalid(matrixCache[ref])) {
      x.makeNan(); // Make it a normal non-matrix-referring nan
      matrixCache[ref] = null;
      return null;
    }
    return matrixCache[ref];
  }

  private void linkToMatrix(Real x, Real xi, Matrix M) {
    x.makeNan();
    xi.makeZero();
    if (M == null || Matrix.isInvalid(M)) {
      // Invalid matrix == nan
      matrixGC();
      return;
    }
    if (M.rows == 1 && M.cols == 1) {
      // 1x1 matrix == Real
      M.getElement(0,0,x,xi);
      matrixGC();
      return;
    }
    matrixGC();
    for (int i=0; i<matrixCache.length; i++)
      if (matrixCache[i] == null) {
        matrixCache[i] = M;
        x.mantissa += i+1; // Create link from x to matrixCache[i]
        return;
      }
    // Out of room in matrix cache, shouldn't happen... (leaving x as nan)
  }

  private void matrixGC() {
    if (!initialized)
      return; // Avoid premature GC before state has been fully restored

    Matrix M;
    for (int i=0; i<matrixCache.length; i++)
      if (matrixCache[i] != null)
        matrixCache[i].refCount = 0;

    for (int i=0; i<STACK_SIZE; i++)
      if ((M = getMatrix(stack[i])) != null)
        M.refCount++;
    if (mem != null)
      for (int i=0; i<MEM_SIZE; i++)
        if ((M = getMatrix(mem[i])) != null)
          M.refCount++;
    if ((M = getMatrix(lastx)) != null) M.refCount++;
    if ((M = getMatrix(lasty)) != null) M.refCount++;
    if ((M = getMatrix(lastz)) != null) M.refCount++;

    for (int i=0; i<matrixCache.length; i++)
      if (matrixCache[i] != null && matrixCache[i].refCount == 0)
        matrixCache[i] = null;
  }

  private void setMonitorY(int row, boolean wrapX) {
    if (maxMonitorSize == 0)
      return;
    if (row < 0) {
      row = maxMonitorSize-1;
      if (wrapX)
        setMonitorX(monitorX-1,false);
    }
    if (row >= maxMonitorSize) {
      row = 0;
      if (wrapX)
        setMonitorX(monitorX+1,false);
    }
    monitorY = row;
    if (monitorY < monitorYOff)
      monitorYOff = monitorY;
    if (monitorY+1 >
        monitorYOff+Math.min(monitorSize,maxMonitorDisplaySize-
                             ((monitorMode==MONITOR_MATRIX || 
                               monitorMode==MONITOR_PROG)?1:0)))
      monitorYOff = monitorY+1-Math.min(monitorSize,maxMonitorDisplaySize-
                                        ((monitorMode==MONITOR_MATRIX|| 
                                          monitorMode==MONITOR_PROG)?1:0));
    if (monitorYOff > Math.max(0, maxMonitorSize-
                               Math.min(monitorSize, maxMonitorDisplaySize-
                               ((monitorMode==MONITOR_MATRIX || 
                                 monitorMode==MONITOR_PROG)?1:0))))
      monitorYOff = Math.max(0, maxMonitorSize-
                             Math.min(monitorSize, maxMonitorDisplaySize-
                             ((monitorMode==MONITOR_MATRIX || 
                               monitorMode==MONITOR_PROG)?1:0)));
    repaintAll();
  }

  private void setMonitorX(int col, boolean wrapY) {
    if (monitorMode != MONITOR_MATRIX || monitoredMatrix == null)
      return;
    // Automatic wrap-around
    if (col < 0) {
      col = monitoredMatrix.cols-1;
      if (wrapY)
        setMonitorY(monitorY-1,false);
    }
    if (col >= monitoredMatrix.cols) {
      col = 0;
      if (wrapY)
        setMonitorY(monitorY+1,false);
    }
    monitorX = col;
    StringBuffer caption = new StringBuffer("Col:");
    caption.append(col+1);
    int nSpaces = (format.maxwidth-caption.length())/2;
    for (int i=0; i<nSpaces; i++)
      caption.append(' ');
    monitorCaption = caption.toString();
    monitors = monitoredMatrix.D[col];
    imagMonitors = monitoredMatrix.DI != null ? monitoredMatrix.DI[col] : null;
    clearMonitorStrings();
  }

  private Matrix getCurrentMatrix() {
    Matrix M = null;
    for (int i=0; i<STACK_SIZE; i++)
      if ((M=getMatrix(stack[i])) != null)
        break;
    return M;
  }

  private void updateMatrixMonitor() {
    Matrix M = getCurrentMatrix();
    if (M == null) {
      monitoredMatrix = null;
      maxMonitorSize = 0;
      monitorSize = 0;
      monitors = imagMonitors = null;
    } else if (M != monitoredMatrix) {
      monitoredMatrix = M;
      maxMonitorSize = M.rows;
      monitorSize = Math.min(initialMonitorSize,maxMonitorSize);
      if (matrixLabels == null || matrixLabels.length < maxMonitorSize) {
        String [] s = new String[maxMonitorSize];
        if (matrixLabels != null)
          System.arraycopy(matrixLabels,0,s,0,matrixLabels.length);
        matrixLabels = s;
      }
      monitorLabels = matrixLabels;
      if (monitorStr.length < maxMonitorSize)
        monitorStr = new String[maxMonitorSize];
      else
        clearMonitorStrings();
      if (monitorX >= M.cols || monitorY >= M.rows) {
        monitorX = 0;
        monitorY = 0;
      }
      setMonitorX(monitorX,false);
      setMonitorY(monitorY,false);
      repaintAll();
    }
  }

  private void updateProgMonitor( boolean pointToEnd ) {
    progLineAddr = new int[progCounter]; // probably too big, but enough
    maxMonitorSize=0;
    for (int i=0; i<progCounter; i++, maxMonitorSize++) {
      progLineAddr[maxMonitorSize]= i;
      if ((prog[currentProg][i] & 0x8000) != 0) 
        i += 5;
    }
    if (monitorStr.length < maxMonitorSize+1)
      monitorStr = new String[maxMonitorSize+1];
    else
      clearMonitorStrings();
    monitorLabels= new String[maxMonitorSize+1];
    for (int i=0; i<maxMonitorSize; i++) {
      monitorLabels[i]= ""+ i;
      int labelWidth = monitorLabels[i].length()+1;
      int a= progLineAddr[i];
      short cmd= prog[currentProg][a];
      if ((cmd & 0x8000) != 0) {
        if (a+5 < prog[currentProg].length) { // Just a precaution
          rTmp.mantissa = (((long)(prog[currentProg][a  ]&0xffff)<<47)+
                           ((long)(prog[currentProg][a+1]&0xffff)<<31)+
                           ((long)(prog[currentProg][a+2]&0xffff)<<15)+
                           ((long)(prog[currentProg][a+3]&0xffff)>>1));
          rTmp.sign     = (byte)(prog[currentProg][a+3]&1);
          rTmp.exponent = (((prog[currentProg][a+4]&0xffff)<<16)+
                           ((prog[currentProg][a+5]&0xffff)));
          format.maxwidth -= labelWidth;
          monitorStr[i] = rTmp.toString(format);
          format.maxwidth += labelWidth;
        }
      } else {
        if ((cmd & MATRIX_STO) != 0) {
          int col = (cmd & 0x3f)+1 ;
          int row = (((cmd>>6)&0x3) + ((cmd>>8)&0x7c))+1;
          cmd &= MATRIX_STO|MATRIX_RCL;
          String s= (cmd == MATRIX_STO) ? "> M:["+row+","+col+"]"
            : "M:["+row+","+col+"] >";
          monitorStr[i] = s.substring(0,Math.min(s.length(),
                                                 format.maxwidth-labelWidth+1));
        } else {
          int param= cmd>>>10;
          cmd &= 0x3ff;
          String tmp= CmdDesc.getStr( cmd, false );
          if ((CmdDesc.getFlags(cmd) & CmdDesc.NUMBER_REQUIRED) != 0 ) 
            tmp += " "+param;
          else if (((CmdDesc.getFlags(cmd) & CmdDesc.FINANCE_REQUIRED) != 0) 
                   || cmd == FINANCE_RCL || cmd == FINANCE_STO )
            tmp += " "+financeLabels[param];
          else if ( cmd == STAT_STO || cmd == STAT_RCL )
            tmp += " "+statLabels[param];
          else if ((CmdDesc.getFlags(cmd) & CmdDesc.PROG_REQUIRED) != 0 )
            tmp += " "+progLabels[cmd]; // for future extensions
          monitorStr[i] = tmp.substring(Math.max(0,tmp.length()
                                                 -format.maxwidth+labelWidth));
        }
      }
    }
    monitorStr[maxMonitorSize] = "[prg end]";
    monitorLabels[maxMonitorSize] = ""+maxMonitorSize;
    maxMonitorSize++;
    monitorSize = Math.min(initialMonitorSize,maxMonitorSize);
    
    if (pointToEnd)
      setMonitorY(-1,false);

    repaintAll();
  }

  private void setMonitoring(int mode, int size, int maxSize,
                             Real[] m, Real [] mi, String[] labels) {
    if (size == 0)
      mode = MONITOR_NONE;

    monitorMode = mode;
    initialMonitorSize = size;
    maxMonitorSize = maxSize;
    monitorSize = Math.min(initialMonitorSize,maxMonitorSize);
    monitors = m;
    imagMonitors = mi;
    monitorLabels = labels;
    setMonitorY(0,false);
    clearMonitorStrings();
  }

  private String makeString(Real x, Real xi) {
    String s;
    Matrix M;
    if ((M = getMatrix(x)) != null) {
      return "M:["+M.rows+"x"+M.cols+"]";
    } else if (xi != null && !xi.isZero()) {
      int maxwidth = format.maxwidth;
      int imagSign = xi.isNegative() && format.base==10 ? 0 : 1;
      format.maxwidth = maxwidth/2-imagSign;
      String imag = xi.toString(format);
      format.maxwidth = maxwidth-1-imagSign-imag.length();
      String real = x.toString(format);
      if (real.length() < format.maxwidth) {
        format.maxwidth = maxwidth-1-imagSign-real.length();
        imag = xi.toString(format);
      }
      s = real+(imagSign!=0?"+":"")+imag+'i';
      format.maxwidth = maxwidth;
    } else {
      s = x.toString(format);
    }
    return s;
  }

  public String getStackElement(int n) {
    if (stackStr[n] == null)
      stackStr[n] = makeString(stack[n],stackI[n]);
    return stackStr[n];
  }

  public boolean hasComplexArgs() {
    Matrix M;
    return !stackI[0].isZero() || !stackI[1].isZero() ||
      ((M = getMatrix(stack[0])) != null && M.DI != null) ||
      ((M = getMatrix(stack[1])) != null && M.DI != null);
  }

  public String getMonitorElement(int n) {
    if (monitorMode == MONITOR_MATRIX || monitorMode == MONITOR_PROG) {
      if (n==0)
        if (monitorMode == MONITOR_MATRIX)  
          if (monitoredMatrix == null)
            return "no matrix";
          else
            return monitorCaption;
        else
          return "Prog: "+progLabels[currentProg];
      else
        n--;
    }
    n += monitorYOff;
    if (monitorStr[n] == null) {
      if (monitorMode == MONITOR_PROG) 
        updateProgMonitor(false);
      else {
        format.maxwidth -= monitorLabels[n].length()+1;
        if (monitors != null && monitors[n] != null) {
          if (imagMonitors != null)
            monitorStr[n] = makeString(monitors[n],imagMonitors[n]);
          else
            monitorStr[n] = makeString(monitors[n],null);
        } else {
          monitorStr[n] = Real.ZERO.toString(format);
        }
        format.maxwidth += monitorLabels[n].length()+1;
      }
    }
    return monitorStr[n];
  }

  public String getMonitorLabel(int n) {
    if (monitorMode == MONITOR_MATRIX  || monitorMode == MONITOR_PROG) {
      if (n==0)
        return "";
      else
        n--;
    }
    n += monitorYOff;
    if (monitorLabels[n]==null) {
      if (monitorMode == MONITOR_MEM)
        monitorLabels[n] = "M"+n;
      else if (monitorMode == MONITOR_MATRIX)
        monitorLabels[n] = "R"+(n+1);
    }
    return monitorLabels[n];
  }

  public String getMonitorLead(int n) {
    if (monitorMode == MONITOR_MATRIX || monitorMode == MONITOR_PROG) {
      if (n==0)
        return "";
      else
        n--;
    }
    n += monitorYOff;
    if (isInsideMonitor && n == monitorY)
      return "�";
    else if (monitorMode == MONITOR_PROG && n == monitorY)
      return ">";
    else
      return "=";
  }

  public int getMonitorSize() {
    return Math.min(monitorSize+((monitorMode == MONITOR_MATRIX ||
                                  monitorMode == MONITOR_PROG) ? 1 : 0),
                    maxMonitorDisplaySize);
  }

  public int getActualMonitorSize() {
    return monitorSize;
  }

  public void setMaxMonitorSize(int max) {
    maxMonitorDisplaySize = max;
    if (monitorMode == MONITOR_MATRIX || monitorMode == MONITOR_PROG )
      maxMonitorDisplaySize--;
    if (monitorY >= monitorYOff+maxMonitorDisplaySize) {
      monitorYOff = monitorY-maxMonitorDisplaySize+1;
      repaintAll();
    }
    if (monitorYOff+Math.min(maxMonitorDisplaySize,monitorSize)>maxMonitorSize)
    {
      monitorYOff = maxMonitorSize-Math.min(maxMonitorDisplaySize,monitorSize);
      repaintAll();
    }
    if (monitorMode == MONITOR_MATRIX || monitorMode == MONITOR_PROG )
      maxMonitorDisplaySize++;
  }

  public void setMaxWidth(int max) {
    format.maxwidth = max;
    if (inputInProgress)
      parseInput();
    clearStrings();
    setMonitorX(monitorX,false); // Possibly update monitorCaption
  }

  private void setMessage(String mc, String m) {
    if (progRunning)
      return; // Ignore messages triggered while program is running
    messageCaption = mc;
    message = m;
    repaintAll(); // Ensure repaint
  }

  public String getMessage() {
    String m = message;
    message = null;
    return m;
  }

  public String getMessageCaption() {
    return messageCaption;
  }

  public int numRepaintLines() {
    int tmp = repaintLines;
    repaintLines = 0;
    return tmp;
  }

  private void repaint(int nElements) {
    if (repaintLines<nElements)
      repaintLines = nElements;
  }

  private void repaintAll() {
    repaint(100);
  }

  private void input(int cmd) {
    int i;
    if (!inputInProgress)
      inputBuf.setLength(0);

    // Switch base to 10 after exponent marker (E)
    int base = format.base;
    char exp = 'e';
    if (cmd >= DIGIT_2 && cmd <= DIGIT_F)
      for (i=0; i<inputBuf.length(); i++)
        if (inputBuf.charAt(i)==exp) {
          base = 10;
          break;
        }

    switch (cmd)
    {
      case CLEAR:
        if (inputBuf.length()==0) {
          inputInProgress = false;
          rollDown(true); // This will undo the rollUp that input started with
          return;
        }
        inputBuf.setLength(inputBuf.length()-1);
        break;

      case DEC_POINT:
        if (inputBuf.length()>0 &&
            inputBuf.charAt(inputBuf.length()-1)==format.point)
        {
          inputBuf.setLength(inputBuf.length()-1);
          break;
        }
        for (i=0; i<inputBuf.length(); i++)
          if (inputBuf.charAt(i)==format.point ||
              inputBuf.charAt(i)==exp)
            return;
        if (inputBuf.length()==0 || inputBuf.charAt(inputBuf.length()-1)=='-')
          inputBuf.append('0');
        else if (inputBuf.length()==1 && inputBuf.charAt(0)=='/')
          inputBuf.append(Real.hexChar.charAt(base-1));
        inputBuf.append(format.point);
        break;

      case SIGN_E:
        if (inputBuf.length()>0 && inputBuf.charAt(inputBuf.length()-1)=='-'){
          inputBuf.setLength(inputBuf.length()-1);
          break;
        }
        if (inputBuf.length()==0 && base!=10) {
          inputBuf.append('/');
          break;
        }
        if (inputBuf.length()==1 && inputBuf.charAt(0)=='/')
          inputBuf.setLength(0);

        if (inputBuf.length()==0 || inputBuf.charAt(inputBuf.length()-1)==exp){
          inputBuf.append('-');
          break;
        }
        for (i=0; i<inputBuf.length(); i++)
          if (inputBuf.charAt(i)==exp)
            return;
        inputBuf.append(exp);
        break;

      case SIGN_POINT_E:
        if (inputBuf.length()>0 && inputBuf.charAt(inputBuf.length()-1)=='-'){
          inputBuf.setLength(inputBuf.length()-1);
          break;
        }
        if (inputBuf.length()==0 && base!=10) {
          inputBuf.append('/');
          break;
        }
        if (inputBuf.length()==1 && inputBuf.charAt(0)=='/')
          inputBuf.setLength(0);

        if (inputBuf.length()==0 || inputBuf.charAt(inputBuf.length()-1)==exp){
          inputBuf.append('-');
          break;
        }
        boolean hasE = false, hasPoint = false;
        for (i=0; i<inputBuf.length(); i++) {
          if (inputBuf.charAt(i)==exp)
            hasE = true;
          if (inputBuf.charAt(i)==format.point)
            hasPoint = true;
        }
        if (!hasE && !hasPoint) {
          inputBuf.append(format.point);
          break;
        }
        if (inputBuf.length()>0 &&
            inputBuf.charAt(inputBuf.length()-1)==format.point)
          inputBuf.setLength(inputBuf.length()-1);
        if (!hasE)
          inputBuf.append(exp);
        break;

      case DIGIT_9:
      case DIGIT_8:
        if (base<10)
          return;
      case DIGIT_7:
      case DIGIT_6:
      case DIGIT_5:
      case DIGIT_4:
      case DIGIT_3:
      case DIGIT_2:
        if (base<8)
          return;
      case DIGIT_0:
      case DIGIT_1:
        inputBuf.append((char)('0'+cmd-DIGIT_0));
        break;
      case DIGIT_A:
      case DIGIT_B:
      case DIGIT_C:
      case DIGIT_D:
      case DIGIT_E:
      case DIGIT_F:
        if (base!=16)
          return;
        if (inputBuf.length()>0) // Just in case... shouldn't happen
          inputBuf.setLength(inputBuf.length()-1);
        inputBuf.append((char)('A'+cmd-DIGIT_A));
        break;

      default:
        return;
    }
    // If routine has not returned yet, we have new input data
    if (!inputInProgress) {
      inputInProgress = true;
      rollUp(true);
    } else {
      repaint(1);
    }
  }

  private void parseInput() {
    lasty.assign(stack[0]);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastyi.assign(stackI[0]);
    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_PUSH;
    stack[0].assign(inputBuf.toString(),format.base);
    stackI[0].makeZero();
    stackStr[0] = null;

    if (progRecording)
      recordPush(stack[0]);

    repaint(1);
    inputInProgress = false;
  }

  private void rollUp(boolean whole) {
    int top;
    if (whole) {
      top = STACK_SIZE-1;
    } else {
      top = getStackHeight()-1;
      if (top<=0)
        return;
    }
    Real tmp = stack[top];
    String tmpStr = stackStr[top];
    Real imagTmp = stackI[top];
    for (int i=top; i>0; i--) {
      stack[i] = stack[i-1];
      stackI[i] = stackI[i-1];
      stackStr[i] = stackStr[i-1];
    }
    stack[0] = tmp;
    stackI[0] = imagTmp;
    stackStr[0] = tmpStr;

    repaintAll();
  }

  private void rollDown(boolean whole) {
    int top;
    if (whole) {
      top = STACK_SIZE-1;
    } else {
      top = getStackHeight()-1;
      if (top<=0)
        return;
    }
    Real tmp = stack[0];
    String tmpStr = stackStr[0];
    Real imagTmp = stackI[0];
    for (int i=0; i<top; i++) {
      stack[i] = stack[i+1];
      stackI[i] = stackI[i+1];
      stackStr[i] = stackStr[i+1];
    }
    stack[top] = tmp;
    stackI[top] = imagTmp;
    stackStr[top] = tmpStr;

    repaintAll();
  }

  private void xchgSt(int n) {
    Real tmp = stack[0];
    stack[0] = stack[n];
    stack[n] = tmp;
    tmp = stackI[0];
    stackI[0] = stackI[n];
    stackI[n] = tmp;
    String tmpStr = stackStr[0];
    stackStr[0] = stackStr[n];
    stackStr[n] = tmpStr;

    repaint(n+1);
  }

  private void enter() {
    rollUp(true);
    lasty.assign(stack[0]);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastyi.assign(stackI[0]);
    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_PUSH;
    stack[0].assign(stack[1]);
    stackI[0].assign(stackI[1]);
    stackStr[0] = stackStr[1];
  }

  private void toRAD(Real x) {
    if (degrees) {
      x.mul(Real.PI);
      x.div(180);
    }
  }

  private void fromRAD(Real x) {
    if (degrees) {
      x.div(Real.PI);
      x.mul(180);
    }
  }

  private static int greatestFactor(int a) {
    if (a==-1)
      return a;
    if (a<0) a = -a;
    if (a<=3)
      return a;
    while ((a&1) == 0) {
      a >>= 1;
      if (a<2*2) return a;
    }
    while (a%3 == 0) {
      a /= 3;
      if (a<3*3) return a;
    }
    int x=5;
    int s=5*5;
    if (a<s) return a;
    do {
      while (a%x == 0) {
        a /= x;
        if (a<s) return a;
      }
      x += 2;
      s = x*x;
      while (a%x == 0) {
        a /= x;
        if (a<s) return a;
      }
      x += 4;
      s = x*x;
    } while (a>=s && x<46343);
    return a;
  }

  private void binary(int cmd) {
    Real x = stack[0];
    Real y = stack[1];
    Real xi = stackI[0];
    Real yi = stackI[1];
    Matrix X = getMatrix(x);
    Matrix Y = getMatrix(y);
    boolean complex   = !xi.isZero() || !yi.isZero();
    boolean complexOk = false;
    boolean matrix    = X != null || Y != null;
    boolean matrixOk  = false;

    Complex.degrees = degrees;

    lastx.assign(x);
    lasty.assign(y);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastxi.assign(xi);
    lastyi.assign(yi);
    undoStackEmpty = stackStr[1]==empty ? stackStr[0]==empty ? 2 : 1 : 0;
    undoOp = UNDO_BINARY;

    switch (cmd)
    {
      case CLEAR:
        matrix = (Y != null);
        matrixOk = true;
        complexOk = true;
        break;

      case ADD:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.add(Y,X);
          } else {
            y.makeNan();
            matrix = false;
          }
        } else {
          complexOk = true;
          y.add(x);
          if (complex) yi.add(xi);
        }
        break;

      case SUB:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.sub(Y,X);
          } else {
            y.makeNan();
            matrix = false;
          }
        } else {
          complexOk = true;
          y.sub(x);
          if (complex) yi.sub(xi);
        }
        break;

      case MUL:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.mul(Y,X);
          } else if (X!=null) {
            Y = Matrix.mul(X,y,yi);
          } else {
            Y = Matrix.mul(Y,x,xi);
          }
        } else if (complex) {
          complexOk = true;
          Complex.mul(y,yi,x,xi);
        } else {
          y.mul(x);
        }
        break;

      case DIV:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.div(Y,X);
          } else if (X!=null) {
            Y = Matrix.div(y,yi,X);
          } else {
            Y = Matrix.div(Y,x,xi);
          }
        } else if (complex) {
          complexOk = true;
          Complex.div(y,yi,x,xi);
        } else {
          y.div(x);
        }
        break;

      case PERCENT_CHG:
        x.sub(y);
        x.div(y);
        x.mul(Real.HUNDRED);
        y.assign(x);
        break;

      case YPOWX:
        if (matrix) {
          matrixOk = true;
          if (X!=null || !x.isIntegral() || complex) {
            y.makeNan();
            matrix = false;
          } else {
            Y = Matrix.pow(Y,x.toInteger());
          }
        } else {
          if (!y.isZero() && y.isNegative() && !x.isIntegral())
            complex = true;
          if (complex) {
            complexOk = true;
            Complex.ln(y,yi);
            Complex.mul(y,yi,x,xi);
            Complex.exp(y,yi);
          } else {
            y.pow(x);
          }
        }
        break;

      case XRTY:
        if (!y.isZero() && y.isNegative() && !(x.isIntegral() && x.isOdd()))
          complex = true;
        if (complex) {
          complexOk = true;
          Complex.ln(y,yi);
          Complex.div(y,yi,x,xi);
          Complex.exp(y,yi);
        } else {
          y.nroot(x);
        }
        break;

      case PYX:
        if (y.isNegative() && x.isIntegral()) {
          // Special case
          // Py,x = (-1)^x * fact(-y-1+x)/fact(-y-1)
          boolean negative = x.isOdd();
          y.neg();
          y.sub(Real.ONE);
          x.add(y);
          x.fact();
          y.fact();
          y.rdiv(x);
          if (negative)
            y.neg();
        } else {
          // Py,x = fact(y)/fact(y-x)
          x.neg();
          x.add(y);
          x.fact();
          y.fact();
          y.div(x);
        }
        break;

      case CYX:
        if (y.isNegative() && x.isIntegral()) {
          // Special case
          // Cy,x = (-1)^x * fact(-y-1+x)/(fact(-y-1) * fact(x))
          boolean negative = x.isOdd();
          rTmp.assign(x);
          rTmp.fact();
          y.neg();
          y.sub(Real.ONE);
          x.add(y);
          x.fact();
          y.fact();
          y.mul(rTmp);
          y.rdiv(x);
          if (negative)
            y.neg();
        } else {
          // Cy,x = fact(y)/(fact(y-x)*fact(x))
          rTmp.assign(x);
          rTmp.fact();
          x.neg();
          x.add(y);
          x.fact();
          x.mul(rTmp);
          y.fact();
          y.div(x);
        }
        break;

      case ATAN2: y.atan2(x); fromRAD(y);  break;
      case HYPOT: y.hypot(x);              break;
      case AND:   y.and(x);                break;
      case OR:    y.or(x);                 break;
      case XOR:   y.xor(x);                break;
      case BIC:   y.bic(x);                break;
      case YUPX:  x.round(); y.scalbn(x.toInteger()); break;
      case YDNX:  x.round(); y.scalbn(-x.toInteger());break;
      case MOD:   y.mod(x);                break;
      case DIVF:  y.divf(x);               break;

      case FINANCE_MULINT:
        y.mul(Real.PERCENT);
        y.add(Real.ONE);
        y.pow(x);
        y.sub(Real.ONE);
        y.mul(Real.HUNDRED);
        break;

      case FINANCE_DIVINT:
        y.mul(Real.PERCENT);
        y.add(Real.ONE);
        y.nroot(x);
        y.sub(Real.ONE);
        y.mul(Real.HUNDRED);
        break;

      case DHMS_PLUS:
        x.fromDHMS();
        y.fromDHMS();
        y.add(x);
        y.toDHMS();
        break;

      case TO_CPLX:
        complexOk = true;
        complex = true;
        yi.assign(y);
        y.assign(x);
        break;

      case MIN:
        if (x.isNan() || y.isNan())
          y.makeNan();
        else if (x.lessThan(y))
          y.assign(x);
        break;

      case MAX:
        if (x.isNan() || y.isNan())
          y.makeNan();
        else if (x.greaterThan(y))
          y.assign(x);
        break;

      case MATRIX_NEW:
        if (!matrix && !complex) {
          int rows = y.toInteger();
          int cols = x.toInteger();
          matrix = true;
          if (rows > 0 && rows < 65536 && cols > 0 && cols < 65536) {
            matrixOk = true;
            Y = new Matrix(rows,cols);
          } else {
            setMessage("Matrix", "Illegal matrix size");
          }
        }
        break;

      case MATRIX_CONCAT:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.concat(Y,X);
          } else if (X!=null) {
            Y = Matrix.concat(y,yi,X);
          } else {
            Y = Matrix.concat(Y,x,xi);
          }
        } else {
          matrix = true;
          matrixOk = true;
          Y = Matrix.concat(y,yi,x,xi);
        }
        break;

      case MATRIX_STACK:
        if (matrix) {
          matrixOk = true;
          if (X!=null && Y!=null) {
            Y = Matrix.stack(Y,X);
          } else if (X!=null) {
            Y = Matrix.stack(y,yi,X);
          } else {
            Y = Matrix.stack(Y,x,xi);
          }
        } else {
          matrix = true;
          matrixOk = true;
          Y = Matrix.stack(y,yi,x,xi);
        }
        break;

      case MATRIX_AIJ:
        // Not matrixOk, arguments must be normal numbers
        Matrix M = getCurrentMatrix();
        int col = x.toInteger()-1;
        int row = y.toInteger()-1;
        if (M == null)
          y.makeNan();
        else
          M.getElement(row,col,y,yi);
        break;
    }

    if (matrix) {
      if (!matrixOk) {
        y.makeNan();
        yi.makeZero();
      } else {
        linkToMatrix(y,yi,Y);
      }
    } else {
      if (complex && !complexOk)
        y.makeNan();
      if (y.isNan() || yi.isNan()) {
        y.makeNan();
        yi.makeZero();
      }
      if (y.isZero() && !yi.isZero())
        y.abs(); // Remove annoying "-"
    }
    degrees = Complex.degrees;

    rollDown(true);
    stack[STACK_SIZE-1].makeZero();
    stackI[STACK_SIZE-1].makeZero();
    stackStr[STACK_SIZE-1] = empty;
    if (cmd != CLEAR)
      stackStr[0] = null;
  }

  private void statAB(Real a, Real b, Real SUMx, Real SUMx2,
                      Real SUMy, Real SUMy2, Real SUMxy)
  {
    // a = (SUMxy-SUMx*SUMy/n)/(SUMx2-sqr(SUMx)/n)
    a.assign(SUMx);
    a.mul(SUMy);
    a.div(SUM1);
    a.neg();
    a.add(SUMxy);
    rTmp.assign(SUMx);
    rTmp.sqr();
    rTmp.div(SUM1);
    rTmp.neg();
    rTmp.add(SUMx2);
    a.div(rTmp);
    // b = (SUMy - a*SUMx)/n
    b.assign(SUMx);
    b.mul(a);
    b.neg();
    b.add(SUMy);
    b.div(SUM1);
  }

  private void unary(int cmd) {
    Real tmp;
    Real x = stack[0];
    Real xi = stackI[0];
    lastx.assign(x);
    lasty.makeZero(); // Clear this in case it is a Matrix reference
    lastz.makeZero(); // ...and this
    lastxi.assign(xi);
    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_UNARY;

    if (!xi.isZero()) {
      // Complex undefined for these operations
      x.makeNan();
      xi.makeZero();
      cmd = -1;
    }

    switch (cmd)
    {
      case FACT:    x.fact();  break;
      case GAMMA:   x.gamma(); break;
      case ERFC:    x.erfc();  break;
      case INVERFC: x.inverfc(); break;
      case NOT:     x.xor(Real.ONE_N); break;
      case TO_DEG:  x.div(Real.PI); x.mul(180); break;
      case TO_RAD:  x.mul(Real.PI); x.div(180); break;
      case TO_DHMS: x.toDHMS(); break;
      case TO_H:    x.fromDHMS(); break;

      case PHI:
        x.mul(Real.SQRT1_2);
        x.neg();
        x.erfc();
        x.scalbn(-1);
        break;
      case INVPHI:
        x.scalbn(1);
        x.inverfc();
        if (!x.isZero())
          x.neg();
        x.mul(Real.SQRT2);
        break;

      case SGN:
        rTmp.assign(Real.ONE);
        rTmp.copysign(x);
        x.assign(rTmp);
        break;

      case DHMS_TO_UNIX:
      case UNIX_TO_DHMS:
        if (cmd == DHMS_TO_UNIX) {
          x.fromDHMS();
          x.sub(17268672);
          x.mul(3600);
        } else {
          x.div(3600);
          x.add(17268672);
          x.toDHMS();
        }
        break;

      case DHMS_TO_JD:
      case JD_TO_DHMS:
        rTmp.assign(3442119);
        rTmp.scalbn(-1);
        if (cmd == DHMS_TO_JD) {
          x.fromDHMS();
          x.div(24);
          x.add(rTmp);
        } else {
          x.sub(rTmp);
          x.mul(24);
          x.toDHMS();
        }
        break;

      case DHMS_TO_MJD:
      case MJD_TO_DHMS:
        if (cmd == DHMS_TO_MJD) {
          x.fromDHMS();
          x.div(24);
          x.sub(678941);
        } else {
          x.add(678941);
          x.mul(24);
          x.toDHMS();
        }
        break;

      case CONV_C_F:
      case CONV_F_C:
        rTmp.assign(0, 0x40000000, 0x7333333333333333L); // 1.8
        if (cmd == CONV_C_F) {
          x.mul(rTmp);
          x.add(32);
        } else {
          x.sub(32);
          x.div(rTmp);
        }
        break;

      case LIN_YEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMx,SUMx2,SUMy,SUMy2,SUMxy);
        x.mul(rTmp2);
        x.add(rTmp3);
        break;

      case LIN_XEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMx,SUMx2,SUMy,SUMy2,SUMxy);
        x.sub(rTmp3);
        x.div(rTmp2);
        break;

      case LOG_YEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMlnx,SUMln2x,SUMy,SUMy2,SUMylnx);
        x.ln();
        x.mul(rTmp2);
        x.add(rTmp3);
        break;

      case LOG_XEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMlnx,SUMln2x,SUMy,SUMy2,SUMylnx);
        x.sub(rTmp3);
        x.div(rTmp2);
        x.exp();
        break;

      case EXP_YEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMx,SUMx2,SUMlny,SUMln2y,SUMxlny);
        x.mul(rTmp2);
        x.add(rTmp3);
        x.exp();
        break;

      case EXP_XEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMx,SUMx2,SUMlny,SUMln2y,SUMxlny);
        x.ln();
        x.sub(rTmp3);
        x.div(rTmp2);
        break;

      case POW_YEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMlnxlny);
        x.ln();
        x.mul(rTmp2);
        x.add(rTmp3);
        x.exp();
        break;

      case POW_XEST:
        allocStat();
        statAB(rTmp2,rTmp3,SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMlnxlny);
        x.ln();
        x.sub(rTmp3);
        x.div(rTmp2);
        x.exp();
        break;
    }
    if (x.isNan())
      x.makeNan(); // In case x refers to a matrix, make it normal nan

    stackStr[0] = null;
    repaint(1);
  }

  private void unaryComplexMatrix(int cmd, int param) {
    Real tmp;
    Real x = stack[0];
    Real xi = stackI[0];
    Matrix X = getMatrix(x);
    boolean complex  = complex = !xi.isZero();
    boolean matrix   = X != null;
    boolean matrixOk = false;

    Complex.degrees = degrees;

    lastx.assign(x);
    lasty.makeZero(); // Clear this in case it is a Matrix reference
    lastz.makeZero(); // ...and this
    lastxi.assign(xi);
    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_UNARY;

    switch (cmd)
    {
      case NEG:
        if (matrix) {
          matrixOk = true;
          X = Matrix.neg(X);
        } else {
          x.neg();
          if (complex) xi.neg();
        }
        break;

      case SQR:
        if (matrix) {
          matrixOk = true;
          X = Matrix.mul(X,X);
        } else if (complex) {
          Complex.sqr(x,xi);
        } else {
          x.sqr();
        }
        break;

      case RECIP:
        if (matrix) {
          matrixOk = true;
          X = Matrix.invert(X);
        } else if (complex) {
          Complex.recip(x,xi);
        } else {
          x.recip();
        }
        break;

      case ABS:
        if (matrix) {
          X.norm_F(x,xi);
          matrix = false;
          complex = !xi.isZero();
        } else if (complex) {
          x.hypot(xi);
          xi.makeZero();
        } else {
          x.abs();
        }
        break;

      case TRANSP:
      case TRANSP_CONJ:
        if (matrix) {
          matrixOk = true;
          X = Matrix.transp(X,cmd==TRANSP_CONJ);
        } // else do nothing
        break;

      case DETERM:
        if (matrix) {
          X.det(x,xi);
          matrix = false;
          complex = !xi.isZero();
        } // else do nothing
        break;

      case TRACE:
        if (matrix) {
          X.trace(x,xi);
          matrix = false;
          complex = !xi.isZero();
        } // else do nothing
        break;

      case PERCENT:
        complex |= !stackI[1].isZero();
        x.mul(Real.PERCENT);
        if (complex) {
          xi.mul(Real.PERCENT);
          Complex.mul(x,xi,stack[1]/*y*/,stackI[1]/*yi*/);
        } else {
          x.mul(stack[1]/*y*/);
        }
        break;

      case SQRT:
        if (complex) {
          Complex.sqrt(x,xi);
        } else if (x.isNegative() && !x.isZero()) {
          complex = true;
          xi.assign(x);
          xi.neg();
          xi.sqrt();
          x.makeZero();
        } else {
          x.sqrt();
        }
        break;

      case CPLX_ARG:
        if (complex) {
          Complex.degrees = false;
          xi.atan2(x);
          x.assign(xi);
          xi.makeZero();
        } else {
          x.makeZero();
        }
        break;

      case CPLX_CONJ:
        if (matrix) {
          matrixOk = true;
          X = Matrix.conj(X);
        } else if (complex) {
          xi.neg();
        }
        break;

      case COS:
        if (complex) {
          x.swap(xi);
          Complex.cosh(x,xi);
          xi.neg();
        } else {
          toRAD(x);
          x.cos();
        }
        break;

      case COSH:
        if (complex) {
          Complex.cosh(x,xi);
        } else {
          x.cosh();
        }
        break;

      case SIN:
        if (complex) {
          Complex.sinh(xi,x);
        } else {
          toRAD(x);
          x.sin();
        }
        break;

      case SINH:
        if (complex) {
          Complex.sinh(x,xi);
        } else {
          x.sinh();
        }
        break;

      case TAN:
        if (complex) {
          xi.neg();
          Complex.tanh(xi,x);
          xi.neg();
        } else {
          toRAD(x);
          x.tan();
        }
        break;

      case TANH:
        if (complex) {
          Complex.tanh(x,xi);
        } else {
          x.tanh();
        }
        break;

      case ASIN:
        if (x.greaterThan(Real.ONE) || x.lessThan(Real.ONE_N))
          complex = true;
        if (complex) {
          Complex.asinh(xi,x);
        } else {
          x.asin();
          fromRAD(x);
        }
        break;

      case ASINH:
        if (complex) {
          Complex.asinh(x,xi);
        } else {
          x.asinh();
        }
        break;

      case ACOS:
        if (x.greaterThan(Real.ONE) || x.lessThan(Real.ONE_N))
          complex = true;
        if (complex) {
          Complex.asinh(xi,x);
          x.neg();
          xi.neg();
          x.add(Real.PI_2);
        } else {
          x.acos();
          fromRAD(x);
        }
        break;

      case ACOSH:
        if (x.lessThan(Real.ONE))
          complex = true;
        if (complex) {
          Complex.acosh(x,xi);
        } else {
          x.acosh();
        }
        break;

      case ATAN:
        if (complex) {
          xi.neg();
          Complex.atanh(xi,x);
          xi.neg();
        } else {
          x.atan();
          fromRAD(x);
        }
        break;

      case ATANH:
        if (x.greaterThan(Real.ONE) || x.lessThan(Real.ONE_N))
          complex = true;
        if (complex) {
          Complex.atanh(x,xi);
        } else {
          x.atanh();
        }
        break;

      case EXP:
        if (complex) {
          Complex.exp(x,xi);
        } else {
          x.exp();
        }
        break;

      case EXP2:
        if (complex) {
          x.mul(Real.LN2);
          xi.mul(Real.LN2);
          Complex.exp(x,xi);
        } else {
          x.exp2();
        }
        break;

      case EXP10:
        if (complex) {
          x.mul(Real.LN10);
          xi.mul(Real.LN10);
          Complex.exp(x,xi);
        } else {
          x.exp10();
        }
        break;

      case LN:
        if (x.isNegative() && !x.isZero())
          complex = true;
        if (complex) {
          Complex.ln(x,xi);
        } else {
          x.ln();
        }
        break;

      case LOG2:
        if (x.isNegative() && !x.isZero())
          complex = true;
        if (complex) {
          Complex.ln(x,xi);
          x.mul(Real.LOG2E);
          xi.mul(Real.LOG2E);
        } else {
          x.log2();
        }
        break;

      case LOG10:
        if (x.isNegative() && !x.isZero())
          complex = true;
        if (complex) {
          Complex.ln(x,xi);
          x.mul(Real.LOG10E);
          xi.mul(Real.LOG10E);
        } else {
          x.log10();
        }
        break;

      case ROUND:
        x.round();
        if (complex) xi.round();
        break;

      case CEIL:
        x.ceil();
        if (complex) xi.ceil();
        break;

      case FLOOR:
        x.floor();
        if (complex) xi.floor();
        break;

      case TRUNC:
        x.trunc();
        if (complex) xi.trunc();
        break;

      case FRAC:
        x.frac();
        if (complex) xi.frac();
        break;

      case XCHGMEM:
        matrixOk = true;
        allocMem();
        tmp = stack[0];
        stack[0] = mem[param];
        mem[param] = tmp;
        tmp = stackI[0];
        stackI[0] = memI[param];
        memI[param] = tmp;
        if (monitorMode == MONITOR_MEM) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;
    }

    if (matrix) {
      if (!matrixOk) {
        x.makeNan();
        xi.makeZero();
      } else {
        linkToMatrix(x,xi,X);
      }
    } else {
      if (x.isNan() || xi.isNan()) {
        x.makeNan();
        xi.makeZero();
      }
      if (x.isZero() && !xi.isZero())
        x.abs(); // Remove annoying "-"
    }
    degrees = Complex.degrees;

    stackStr[0] = null;
    repaint(1);
  }

  private void xyOp(int cmd) {
    Real x = stack[0];
    Real y = stack[1];
    Real xi = stackI[0];
    Real yi = stackI[1];
    lastx.assign(x);
    lasty.assign(y);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastxi.assign(xi);
    lastyi.assign(yi);
    boolean matrix = false;
    undoStackEmpty = stackStr[1]==empty ? stackStr[0]==empty ? 2 : 1 : 0;
    undoOp = UNDO_XY;

    switch (cmd)
    {
      case RP:
        rTmp.assign(y);
        rTmp.atan2(x);
        x.hypot(y);
        y.assign(rTmp);
        fromRAD(y);
        xi.makeZero();
        yi.makeZero();
        break;

      case PR:
        toRAD(y);
        rTmp.assign(y);
        rTmp.cos();
        y.sin();
        y.mul(x);
        x.mul(rTmp);
        xi.makeZero();
        yi.makeZero();
        break;

      case MATRIX_SPLIT:
        Matrix Y = getMatrix(y);
        int n = x.toInteger();
        matrix = true;
        if (Y != null && ((n>=0 && n<=Y.rows) || (n<0 && -n<=Y.cols))) {
          Matrix A,B;
          if (n>=0) {
            A = Y.subMatrix(0,0,n,Y.cols);
            B = Y.subMatrix(n,0,Y.rows-n,Y.cols);
          } else {
            n = -n;
            A = Y.subMatrix(0,0,Y.rows,n);
            B = Y.subMatrix(0,n,Y.rows,Y.cols-n);
          }
          linkToMatrix(y,yi,A);
          linkToMatrix(x,xi,B);
        } // else do nothing
        break;
    }
    if (!matrix) {
      if (x.isNan()) x.makeNan();// In case x refers to matrix, make normal nan
      if (y.isNan()) y.makeNan();// ...ditto
    }
    stackStr[0] = null;
    stackStr[1] = null;
    repaint(2);
  }

  private void push(Real x, Real xi) {
    rollUp(true);
    lasty.assign(stack[0]);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastyi.assign(stackI[0]);
    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_PUSH;
    stack[0].assign(x);
    stackI[0].assign(xi);
    stackStr[0] = null;
  }

  private void push(int e, long m) {
    rTmp.assign(0,e,m);
    push(rTmp,null);
  }

  private void cond(int cmd) {
    Real x = stack[0];
    Real y = stack[1];
    Real xi = stackI[0];
    Real yi = stackI[1];

    if (!xi.isZero()|| !yi.isZero()) {
      switch (cmd) {
        case IF_EQUAL:
          push(x.equalTo(y) && xi.equalTo(yi) ? Real.ONE:Real.ZERO, null);
          break;
        case IF_NEQUAL:
          push(x.notEqualTo(y) || xi.notEqualTo(yi) ?Real.ONE:Real.ZERO, null);
          break;
        case IF_LESS:
        case IF_LEQUAL:
        case IF_GREATER:
          // Perhaps compare absolute values?
          push(Real.NAN, null);
          break;
      }
      return;
    }

    Matrix X = getMatrix(x);
    Matrix Y = getMatrix(y);
    if (X != null || Y != null) {
      if (X == null || Y == null) {
        push(Real.NAN, null);
        return;
      }
      switch (cmd) {
        case IF_EQUAL:
          push(Matrix.equals(X,Y) ? Real.ONE:Real.ZERO, null);
          break;
        case IF_NEQUAL:
          push(Matrix.notEquals(X,Y) ? Real.ONE:Real.ZERO, null);
          break;
        case IF_LESS:
        case IF_LEQUAL:
        case IF_GREATER:
          push(Real.NAN, null);
          break;
      }
      return;
    }

    switch (cmd) {
      case IF_EQUAL:   push(x.equalTo(y)    ?Real.ONE:Real.ZERO, null); break;
      case IF_NEQUAL:  push(x.notEqualTo(y) ?Real.ONE:Real.ZERO, null); break;
      case IF_LESS:    push(x.lessThan(y)   ?Real.ONE:Real.ZERO, null); break;
      case IF_LEQUAL:  push(x.lessEqual(y)  ?Real.ONE:Real.ZERO, null); break;
      case IF_GREATER: push(x.greaterThan(y)?Real.ONE:Real.ZERO, null); break;
    }
  }

  private void trinary(int cmd) {
    Real x = stack[0];
    Real y = stack[1];
    Real z = stack[2];
    Real xi = stackI[0];
    Real yi = stackI[1];
    Real zi = stackI[2];
    boolean complex = !xi.isZero() || !yi.isZero() || !zi.isZero();

    lastx.assign(x);
    lasty.assign(y);
    lastz.assign(z);
    lastxi.assign(xi);
    lastyi.assign(yi);
    lastzi.assign(zi);
    undoStackEmpty = stackStr[2]==empty ? stackStr[1]==empty ?
      stackStr[0]==empty ? 3 : 2 : 1 : 0;
    undoOp = UNDO_TRINARY;

    Matrix X = getMatrix(x);
    Matrix Y = getMatrix(y);
    Matrix Z = getMatrix(z);
    boolean matrix = X != null || Y != null || Z != null;

    switch (cmd)
    {
      case SELECT:
        // Calculate x*y + (1-x)*z
        if (x.isZero() && xi.isZero()) {
          // We're done
        } else if (x.equalTo(Real.ONE) && xi.isZero()) {
          z.assign(y);
          zi.assign(yi);
        } else {
          if (matrix) {
            if (X != null) {
              if (X.cols != X.rows || ((Y == null) != (Z == null))) {
                matrix = false;
                z.makeNan();
              } else {
                // Weird, but perhaps sometime it will be useful
                Matrix Tmp = new Matrix(X.cols);
                for (int i=0; i<X.cols; i++)
                  Tmp.setElement(i,i,Real.ONE,null);
                Tmp = Matrix.sub(Tmp,X);
                if (Y == null /*&& Z == null*/) {
                  // X*y + (I-X)*z
                  Z = Matrix.mul(Tmp,z,zi);
                  Y = Matrix.mul(X,y,yi);
                } else {
                  // X*Y + (I-X)*Z
                  Z = Matrix.mul(Tmp,Z);
                  Y = Matrix.mul(X,Y);
                }
                Z = Matrix.add(Z,Y);
              }
            } else {
              if (Y == null || Z == null) {
                matrix = false;
                z.makeNan();
              } else {
                // x*Y + (1-x)*Z
                rTmp3.assign(Real.ONE);
                rTmp3.sub(x);
                rTmp4.assign(xi);
                rTmp4.neg();
                Z = Matrix.mul(Z,rTmp3,rTmp4);
                Y = Matrix.mul(Y,x,xi);
                Z = Matrix.add(Z,Y);
              }
            }
          } else {
            rTmp3.assign(Real.ONE);
            rTmp3.sub(x);
            if (complex) {
              rTmp4.assign(xi);
              rTmp4.neg();
              Complex.mul(z,zi,rTmp3,rTmp4);
              Complex.mul(y,yi,x,xi);
              z.add(y);
              zi.add(yi);
            } else {
              z.mul(rTmp3);
              y.mul(x);
              z.add(y);
            }
          }
        }
        break;
    }
    // Result is in z...
    if (matrix) {
      linkToMatrix(z,zi,Z);
    } else {
      if (z.isNan() || zi.isNan()) {
        z.makeNan();
        zi.makeZero();
      }
      if (z.isZero() && !zi.isZero())
        z.abs(); // Remove annoying "-"
    }

    rollDown(true);
    rollDown(true);
    stack[STACK_SIZE-1].makeZero();
    stack[STACK_SIZE-2].makeZero();
    stackI[STACK_SIZE-1].makeZero();
    stackI[STACK_SIZE-2].makeZero();
    stackStr[STACK_SIZE-1] = empty;
    stackStr[STACK_SIZE-2] = empty;
    stackStr[0] = null;
  }

  private void sum(int cmd) {
    allocStat();
    Real x = stack[0];
    Real y = stack[1];
    int index;

    switch (cmd)
    {
      case SUMPL:
        index = (statLogStart+statLogSize)%STATLOG_SIZE;
        statLog[index*2] = x.toFloatBits();
        statLog[index*2+1] = y.toFloatBits();
        if (statLogSize < STATLOG_SIZE)
          statLogSize++;
        else
          statLogStart = (statLogStart+1)%STATLOG_SIZE;

        SUM1.add(Real.ONE);
        SUMx.add(x);
        rTmp.assign(x);
        rTmp.sqr();
        SUMx2.add(rTmp);
        SUMy.add(y);
        rTmp.assign(y);
        rTmp.sqr();
        SUMy2.add(rTmp);
        rTmp.assign(x);
        rTmp.mul(y);
        SUMxy.add(rTmp);
        rTmp2.assign(x);
        rTmp2.ln();
        SUMlnx.add(rTmp2);
        rTmp.assign(rTmp2);
        rTmp.sqr();
        SUMln2x.add(rTmp);
        rTmp3.assign(y);
        rTmp3.ln();
        SUMlny.add(rTmp3);
        rTmp.assign(rTmp3);
        rTmp.sqr();
        SUMln2y.add(rTmp);
        rTmp.assign(x);
        rTmp.mul(rTmp3);
        SUMxlny.add(rTmp);
        rTmp.assign(y);
        rTmp.mul(rTmp2);
        SUMylnx.add(rTmp);
        rTmp2.mul(rTmp3);
        SUMlnxlny.add(rTmp2);
        break;

      case SUMMI:
        // Statistics log: search for point and remove if found
        int xf = x.toFloatBits();
        int yf = y.toFloatBits();
        for (int i=statLogSize-1; i>=0; i--) {
          index = (statLogStart+i)%STATLOG_SIZE;
          if (statLog[index*2]==xf && statLog[index*2+1]==yf) {
            statLogSize--;
            for (; i<statLogSize; i++) {
              int index2 = (statLogStart+i+1)%STATLOG_SIZE;
              statLog[index*2] = statLog[index2*2];
              statLog[index*2+1] = statLog[index2*2+1];
              index = index2;
            }
            break;
          }
        }

        SUM1.sub(Real.ONE);
        SUMx.sub(x);
        rTmp.assign(x);
        rTmp.sqr();
        SUMx2.sub(rTmp);
        SUMy.sub(y);
        rTmp.assign(y);
        rTmp.sqr();
        SUMy2.sub(rTmp);
        rTmp.assign(x);
        rTmp.mul(y);
        SUMxy.sub(rTmp);
        rTmp2.assign(x);
        rTmp2.ln();
        SUMlnx.sub(rTmp2);
        rTmp.assign(rTmp2);
        rTmp.sqr();
        SUMln2x.sub(rTmp);
        rTmp3.assign(y);
        rTmp3.ln();
        SUMlny.sub(rTmp3);
        rTmp.assign(rTmp3);
        rTmp.sqr();
        SUMln2y.sub(rTmp);
        rTmp.assign(x);
        rTmp.mul(rTmp3);
        SUMxlny.sub(rTmp);
        rTmp.assign(y);
        rTmp.mul(rTmp2);
        SUMylnx.sub(rTmp);
        rTmp2.mul(rTmp3);
        SUMlnxlny.sub(rTmp2);
        break;
    }
    if (monitorMode == MONITOR_STAT) {
      clearMonitorStrings();
      repaintAll();
    }
    push(SUM1,null);
  }

  private void stat2(int cmd) {
    rollUp(true);
    rollUp(true);
    lasty.assign(stack[0]);
    lastz.assign(stack[1]);
    lastyi.assign(stackI[0]);
    lastzi.assign(stackI[1]);
    stackI[0].makeZero();
    stackI[1].makeZero();

    undoStackEmpty = stackStr[1]==empty ? stackStr[0]==empty ? 2 : 1 : 0;
    undoOp = UNDO_PUSH2;
    Real x = stack[0];
    Real y = stack[1];

    switch (cmd)
    {
      case AVG:
        allocStat();
        // x_avg = SUMx/n
        x.assign(SUMx);
        x.div(SUM1);
        // y_avg = SUMy/n
        y.assign(SUMy);
        y.div(SUM1);
        break;

      case STDEV:
      case PSTDEV:
        allocStat();
        // s_x = sqrt((SUMx2-sqr(SUMx)/n)/(n-1))
        // S_x = sqrt((SUMx2-sqr(SUMx)/n)/n)
        x.assign(SUMx);
        x.sqr();
        x.div(SUM1);
        x.neg();
        x.add(SUMx2);
        rTmp.assign(SUM1);
        if (cmd == STDEV)
          rTmp.sub(Real.ONE);
        x.div(rTmp);
        x.sqrt();
        // s_y = sqrt((SUMy2-sqr(SUMy)/n)/(n-1))
        // S_y = sqrt((SUMy2-sqr(SUMy)/n)/n)
        y.assign(SUMy);
        y.sqr();
        y.div(SUM1);
        y.neg();
        y.add(SUMy2);
        y.div(rTmp);
        y.sqrt();
        break;

      case LIN_AB:
        allocStat();
        statAB(x,y,SUMx,SUMx2,SUMy,SUMy2,SUMxy);
        break;

      case LOG_AB:
        allocStat();
        statAB(x,y,SUMlnx,SUMln2x,SUMy,SUMy2,SUMylnx);
        break;

      case EXP_AB:
        allocStat();
        statAB(x,y,SUMx,SUMx2,SUMlny,SUMln2y,SUMxlny);
        y.exp();
        break;

      case POW_AB:
        allocStat();
        statAB(x,y,SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMlnxlny);
        y.exp();
        break;

      case MATRIX_SIZE:
        Matrix M = getCurrentMatrix();
        if (M == null) {
          x.makeNan();
          y.makeNan();
        } else {
          x.assign(M.cols);
          y.assign(M.rows);
        }
        break;
    }
    stackStr[0] = null;
    stackStr[1] = null;
  }

  private void statR(Real r, Real SUMx, Real SUMx2,
                     Real SUMy, Real SUMy2, Real SUMxy)
  {
    // r =(SUMxy-SUMx*SUMy/n)/sqrt((SUMx2-sqr(SUMx)/n)*(SUMy2-sqr(SUMy)/n))
    r.assign(SUMx);
    r.sqr();
    r.div(SUM1);
    r.neg();
    r.add(SUMx2);
    rTmp.assign(SUMy);
    rTmp.sqr();
    rTmp.div(SUM1);
    rTmp.neg();
    rTmp.add(SUMy2);
    r.mul(rTmp);
    r.rsqrt();
    rTmp.assign(SUMx);
    rTmp.mul(SUMy);
    rTmp.div(SUM1);
    rTmp.neg();
    rTmp.add(SUMxy);
    r.mul(rTmp);
  }

  private void stat1(int cmd) {
    allocStat();
    rollUp(true);
    lasty.assign(stack[0]);
    lastz.makeZero(); // Clear this in case it is a Matrix reference
    lastyi.assign(stackI[0]);
    stackI[0].makeZero();

    undoStackEmpty = stackStr[0]==empty ? 1 : 0;
    undoOp = UNDO_PUSH;
    Real x = stack[0];

    switch (cmd)
    {
      case AVGXW:
        x.assign(SUMxy);
        x.div(SUMy);
        break;
      case LIN_R:
        statR(x,SUMx,SUMx2,SUMy,SUMy2,SUMxy);
        break;
      case LOG_R:
        statR(x,SUMlnx,SUMln2x,SUMy,SUMy2,SUMylnx);
        break;
      case EXP_R:
        statR(x,SUMx,SUMx2,SUMlny,SUMln2y,SUMxlny);
        break;
      case POW_R:
        statR(x,SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMlnxlny);
        break;
    }
    stackStr[0] = null;
  }

  private void financeSolve(int which) {
    allocFinance();

    switch (which)
    {
      case 0: // PV
        if (IR.isZero()) {
          // pv = -(np*pmt+fv)
          PV.assign(NP);
          PV.mul(PMT);
          PV.add(FV);
          PV.neg();
        } else {
          // pv = -(((1+ir)^np-1)*pmt*(1+ir*bgn)/ir + fv)/((1+ir)^np)
          rTmp.assign(IR);
          rTmp.mul(Real.PERCENT);
          rTmp2.assign(rTmp);
          rTmp2.add(Real.ONE);
          rTmp2.pow(NP);
          PV.assign(rTmp2);
          PV.sub(Real.ONE);
          PV.mul(PMT);
          PV.div(rTmp);
          if (begin) {
            rTmp.add(Real.ONE);
            PV.mul(rTmp);
          }
          PV.add(FV);
          PV.div(rTmp2);
          PV.neg();
        }
        break;

      case 1: // FV
        if (IR.isZero()) {
          // fv = -(np*pmt+pv)
          FV.assign(NP);
          FV.mul(PMT);
          FV.add(PV);
          FV.neg();
        } else {
          // fv = -((1+ir)^np*pv + ((1+ir)^np - 1)*pmt*(1+ir*bgn)/ir)
          rTmp.assign(IR);
          rTmp.mul(Real.PERCENT);
          rTmp2.assign(rTmp);
          rTmp2.add(Real.ONE);
          rTmp2.pow(NP);
          FV.assign(rTmp2);
          FV.mul(PV);
          rTmp2.sub(Real.ONE);
          rTmp2.mul(PMT);
          rTmp2.div(rTmp);
          if (begin) {
            rTmp.add(Real.ONE);
            rTmp2.mul(rTmp);
          }
          FV.add(rTmp2);
          FV.neg();
        }
        break;

      case 2: // NP
        if (IR.isZero()) {
          // np = -(fv+pv)/pmt
          NP.assign(FV);
          NP.add(PV);
          if (!NP.isZero()) {
            NP.div(PMT);
            NP.neg();
          }
        } else {
          // np = ln((pmt/ir + pmt*bgn - fv) /
          //         (pmt/ir + pmt*bgn + pv)) /
          //      ln(1 + ir)
          rTmp.assign(IR);
          rTmp.mul(Real.PERCENT);
          NP.assign(PMT);
          NP.div(rTmp);
          if (begin)
            NP.add(PMT);
          rTmp2.assign(NP);
          NP.sub(FV);
          rTmp2.add(PV);
          NP.div(rTmp2);
          NP.ln();
          rTmp.add(Real.ONE);
          rTmp.ln();
          NP.div(rTmp);
        }
        break;

      case 3: // PMT
        // pmt = -(fv+pv)/np
        if (IR.isZero()) {
          PMT.assign(FV);
          PMT.add(PV);
          if (!PMT.isZero()) {
            PMT.div(NP);
            PMT.neg();
          }
        } else {
          // pmt = -(((1+ir)^np*pv+fv)*ir)/(((1+ir)^np-1)*(1 + ir*bgn));
          rTmp.assign(IR);
          rTmp.mul(Real.PERCENT);
          rTmp2.assign(rTmp);
          rTmp2.add(Real.ONE);
          rTmp2.pow(NP);
          PMT.assign(rTmp2);
          PMT.mul(PV);
          PMT.add(FV);
          PMT.mul(rTmp);
          rTmp2.sub(Real.ONE);
          if (begin) {
            rTmp.add(Real.ONE);
            rTmp2.mul(rTmp);
          }
          PMT.div(rTmp2);
          PMT.neg();
        }
        break;

      case 4: // IR
        if ((FV.isZero() && PV.isZero()) ||
            !PV.isFinite() ||
            !FV.isFinite() ||
            !NP.isFinite() ||
            !PMT.isFinite())
        {
          IR.makeNan();
          break;
        }
        // Educated guess at start value = 2*(np*pmt+pv+fv)/(np*(fv-pv))
        rTmp.assign(FV);
        rTmp.sub(PV);
        rTmp.mul(NP);
        if (!rTmp.isZero()) {
          IR.assign(NP);
          IR.mul(PMT);
          IR.add(PV);
          IR.add(FV);
          IR.scalbn(1);
          IR.div(rTmp);
        } else {
          // If start value fails, use ir itself
          IR.mul(Real.PERCENT);
        }
        if (!IR.isFiniteNonZero()) {
          IR.assign(Real.TEN);
          IR.pow(-2); // When all else fails, just start with 1%
        }
        Real X1 = new Real();
        Real Y1 = new Real();
        Real X2 = new Real();
        Real Y2 = new Real();
        for (int n=0; n<20; n++) {
          if (n>=2) {
            // Calculate secant approximation:
            // ir = X1 - Y1*(X1-X2)/(Y1-Y2)
            rTmp.assign(X1);
            rTmp.sub(X2);
            rTmp2.assign(Y1);
            rTmp2.sub(Y2);
            if (!rTmp.isFinite() || !rTmp2.isFinite() ||
                rTmp.exponent <= 0x40000000-56 ||
                rTmp2.exponent <= 0x40000000-46)
              break;
            rTmp.div(rTmp2);
            rTmp.mul(Y1);
            rTmp.neg();
            rTmp.add(X1);
            IR.assign(rTmp);
          } else if (n==1) {
            // Use ir and 2*ir as starting values
            IR.scalbn(1);
          }
          if (IR.isZero()) {
            // f(ir) = fv + np*pmt + pv
            rTmp.assign(NP);
            rTmp.mul(PMT);
            rTmp.add(PV);
          } else {
            // f(ir) = fv + (1+ir)^np*pv + ((1+ir)^np - 1)*pmt*(1+ir*bgn)/ir
            rTmp2.assign(IR);
            rTmp2.add(Real.ONE);
            rTmp2.pow(NP);
            rTmp.assign(rTmp2);
            rTmp.mul(PV);
            rTmp2.sub(Real.ONE);
            rTmp2.mul(PMT);
            rTmp2.div(IR);
            if (begin) {
              rTmp3.assign(IR);
              rTmp3.add(Real.ONE);
              rTmp2.mul(rTmp3);
            }
            rTmp.add(rTmp2);
          }
          rTmp.add(FV);

          X2.assign(X1);
          Y2.assign(Y1);
          X1.assign(IR);
          Y1.assign(rTmp);
        }
        if (!Y1.isFinite() || Y1.exponent > 0x40000000-16) {
          // We didn't reach zero
          IR.makeNan();
          break;
        }
        IR.mul(Real.HUNDRED);
        break;
    }
    push(finance[which],null);
  }

  private void undo() {
    switch (undoOp)
    {
      case UNDO_NONE:
        break;

      case UNDO_UNARY:
        stack[0].assign(lastx);
        stackI[0].assign(lastxi);
        stackStr[0] = undoStackEmpty >= 1 ? empty : null;
        repaint(1);
        break;

      case UNDO_BINARY:
        rollUp(true);
        stack[0].assign(lastx);
        stack[1].assign(lasty);
        stackI[0].assign(lastxi);
        stackI[1].assign(lastyi);
        stackStr[0] = undoStackEmpty >= 2 ? empty : null;
        stackStr[1] = undoStackEmpty >= 1 ? empty : null;
        break;

      case UNDO_TRINARY:
        rollUp(true);
        rollUp(true);
        stack[0].assign(lastx);
        stack[1].assign(lasty);
        stack[2].assign(lastz);
        stackI[0].assign(lastxi);
        stackI[1].assign(lastyi);
        stackI[2].assign(lastzi);
        stackStr[0] = undoStackEmpty >= 3 ? empty : null;
        stackStr[1] = undoStackEmpty >= 2 ? empty : null;
        stackStr[2] = undoStackEmpty >= 1 ? empty : null;
        break;

      case UNDO_PUSH:
        stack[0].assign(lasty);
        stackI[0].assign(lastyi);
        stackStr[0] = undoStackEmpty >= 1 ? empty : null;
        rollDown(true);
        break;

      case UNDO_PUSH2:
        stack[0].assign(lasty);
        stack[1].assign(lastz);
        stackI[0].assign(lastyi);
        stackI[1].assign(lastzi);
        stackStr[0] = undoStackEmpty >= 2 ? empty : null;
        stackStr[1] = undoStackEmpty >= 1 ? empty : null;
        rollDown(true);
        rollDown(true);
        break;

      case UNDO_XY:
        stack[0].assign(lastx);
        stack[1].assign(lasty);
        stackI[0].assign(lastxi);
        stackI[1].assign(lastyi);
        stackStr[0] = undoStackEmpty >= 2 ? empty : null;
        stackStr[1] = undoStackEmpty >= 1 ? empty : null;
        repaint(2);
        break;

      case UNDO_PUSHXY:
        stack[0].assign(lasty);
        stack[1].assign(lastx);
        stackI[0].assign(lastyi);
        stackI[1].assign(lastxi);
        stackStr[0] = undoStackEmpty >= 1 ? empty : null;
        stackStr[1] = undoStackEmpty >= 2 ? empty : null;
        // Different this time         ^^^
        rollDown(true);
        break;

      case UNDO_ROLLDN:
        rollUp(false);
        break;

      case UNDO_ROLLUP:
        rollDown(false);
        break;

      case UNDO_XCHGST:
        xchgSt(undoStackEmpty);
        break;
    }
    lasty.makeZero(); // Clear this in case it is a Matrix reference
    lastz.makeZero(); // ...and this
    undoOp = UNDO_NONE; // Cannot undo this
  }

  private void record(int cmd, int param) {
    if (prog[currentProg] == null ||
        (cmd >= PROG_EDIT   && cmd <= PROG_DIFF) ||
        (cmd >= AVG_DRAW    && cmd <= POW_DRAW) ||
        (cmd >= PROG_DRAW   && cmd <= PROG_MINMAX) ||
        (cmd == MONITOR_PROG))
      return; // Such commands cannot be recorded

    if (progCounter == prog[currentProg].length) {
      matrixGC();
      short [] prog2 = new short[prog[currentProg].length*2];
      System.arraycopy(prog[currentProg],0,prog2,0,progCounter);
      prog[currentProg] = prog2;
    }

    int inspos= progCounter;
    if ( monitorMode == MONITOR_PROG  && monitorY < maxMonitorSize-1) {
      inspos= progLineAddr[monitorY];
      System.arraycopy(prog[currentProg],inspos,prog[currentProg],
                       inspos+1, progCounter-inspos);
    }
    
    if (cmd == MATRIX_STO || cmd == MATRIX_RCL) {
      // Special case, utilizing 9 bits to store row/col
      int col = param&0xffff;
      int row = (param>>16)&0xffff;
      if (col>=64 || row>=128)
        return; // Cannot program so large index (should we warn?)
      cmd += col+((row&0x3)<<6)+((row&0x7c)<<8);
      prog[currentProg][inspos++] = (short)cmd;
    } else {
      prog[currentProg][inspos++] = (short)(cmd+(param<<10));
    }
    progCounter++;
    if (monitorMode == MONITOR_PROG) {
      updateProgMonitor( false );
      setMonitorY( monitorY+1, false );
    }
  }

  private void recordPush(Real x) {
    if (progCounter+6 > prog[currentProg].length) {
      matrixGC();
      short [] prog2 = new short[prog[currentProg].length*2];
      System.arraycopy(prog[currentProg],0,prog2,0,progCounter);
      prog[currentProg] = prog2;
    }
    int inspos= progCounter;
    if ( monitorMode == MONITOR_PROG  && monitorY < maxMonitorSize-1) {
      inspos= progLineAddr[monitorY];
      int d= (x.isZero() || x.isInfinity()) ? 1 : 6;
      System.arraycopy(prog[currentProg],inspos,prog[currentProg],
                       inspos+d, progCounter-inspos);
    }
    if (x.isZero()) {
      prog[currentProg][inspos++] = (short)(PUSH_ZERO + x.sign);
      progCounter++;
    } else if (x.isInfinity()) {
      prog[currentProg][inspos++] = (short)(PUSH_INF + x.sign);
      progCounter++;
    } else {
      prog[currentProg][inspos++] = (short)(x.mantissa>>47);
      prog[currentProg][inspos++] = (short)(x.mantissa>>31);
      prog[currentProg][inspos++] = (short)(x.mantissa>>15);
      prog[currentProg][inspos++] = (short)((x.mantissa<<1)+x.sign);
      prog[currentProg][inspos++] = (short)(x.exponent>>16);
      prog[currentProg][inspos++] = (short)(x.exponent);
      progCounter += 6;
    }
    if (monitorMode == MONITOR_PROG) {
      updateProgMonitor( false );
      if (monitorY >= monitorYOff+maxMonitorDisplaySize) 
        monitorYOff = monitorY-maxMonitorDisplaySize+1;
      setMonitorY( monitorY+1, false );
    }
  }

  // execute instruction at address a in program
  // returns position of next instruction
  private int executePrgAddr( int a ) {
    short cmd = prog[currentProg][a];
    if ((cmd & 0x8000) == 0) {
      if ((cmd & MATRIX_STO) != 0) {
        int col = cmd & 0x3f;
        int row = ((cmd>>6)&0x3) + ((cmd>>8)&0x7c);
        cmd &= MATRIX_STO|MATRIX_RCL;
        command(cmd,(row<<16)+col);
      } else {
        command(cmd&0x3ff, cmd>>>10);
      }
      return a+1;
    } else {
      if (a+5 < prog[currentProg].length) { // Just a precaution
        rTmp.mantissa = (((long)(prog[currentProg][a  ]&0xffff)<<47)+
                         ((long)(prog[currentProg][a+1]&0xffff)<<31)+
                         ((long)(prog[currentProg][a+2]&0xffff)<<15)+
                         ((long)(prog[currentProg][a+3]&0xffff)>>1));
        rTmp.sign     = (byte)(prog[currentProg][a+3]&1);
        rTmp.exponent = (((prog[currentProg][a+4]&0xffff)<<16)+
                         ((prog[currentProg][a+5]&0xffff)));
        push(rTmp,null);
     
      }
      return a+6;     
    }
  }

  private void executeProgram() {
    for (int i=0; i<prog[currentProg].length; ) {
      i = executePrgAddr( i );
    }
    if (inputInProgress) // From the program...
      parseInput();
  }

  private void singleStepProgram( int step ) {
    if ( monitorMode != MONITOR_PROG || 
         step > maxMonitorSize-1 )
      return;
    executePrgAddr( progLineAddr[step] );
    if (inputInProgress) 
      parseInput();
  }

  private void differentiateProgram() {
    Real x   = new Real(stack[0]);
    Real xi  = new Real(stackI[0]);
    Real h   = new Real();
    Real y1  = new Real();
    Real y1i = new Real();
    Real y2  = new Real();
    Real y2i = new Real();

    push(Real.NAN,null);

    if (!x.isFinite() || !xi.isFinite()) {
      // Abnormal x. (nan is already pushed)
      return;
    }

    Real.magicRounding = false;
    h.assign(Real.ONE);
    h.scalbn(Math.max(-21, x.exponent-0x40000000-21));

    boolean finished = false;

    for (int n=0; n<3 && !finished; n++)
    {
      // y1 = f(x-h);
      stack[0].assign(x);
      stackI[0].assign(xi);
      stack[0].sub(h);
      executeProgram();
      y1.assign(stack[0]);
      y1i.assign(stackI[0]);

      // y2 = f(x+h);
      stack[0].assign(x);
      stackI[0].assign(xi);
      stack[0].add(h);
      executeProgram();
      y2.assign(stack[0]);
      y2i.assign(stackI[0]);

      if (!y1.isFinite() || !y1i.isFinite() ||
          !y2.isFinite() || !y2i.isFinite())
      {
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        return;
      }

      int exp = Math.max(y1.exponent, y2.exponent);
      exp = Math.max(exp, y1i.exponent);
      exp = Math.max(exp, y2i.exponent);

      rTmp.assign(y1);
      rTmp.add(y2);
      int exp3 = rTmp.exponent;
      rTmp.assign(y1i);
      rTmp.add(y2i);
      exp3 = Math.max(exp3, rTmp.exponent);
      y2.sub(y1);
      y2i.sub(y1i);
      int exp2 = Math.max(y2.exponent, y2i.exponent);

      if (exp3 < exp-5)       // i.e. y1 == -y2  => pathological case
        finished = true;
      else if (exp-exp2 > 63) // i.e. y1 == y2   => pathological case
        h.scalbn(42);
      else if (exp-exp2 > 22 || exp-exp2 < 20) {
        // Try to adjust h so that 2/3 of the bits of y2-y1 are valid
        h.scalbn((exp-exp2)-21);
        if (x.exponent - h.exponent > 59)
          h.exponent = x.exponent-59;
      } else
        finished = true;
    }
    y2.div(h);
    y2i.div(h);
    y2.scalbn(-1);
    y2i.scalbn(-1);
    stack[0].assign(y2);
    stackI[0].assign(y2i);
    Real.magicRounding = true;
  }

  public void command(int cmd, int param) throws OutOfMemoryError {
    int i;

    // The following commands are NOT recorded in a program,
    // but they may call other "command"s which are programmed
    switch (cmd)
    {
      case DIGIT_0: case DIGIT_1: case DIGIT_2: case DIGIT_3:
      case DIGIT_4: case DIGIT_5: case DIGIT_6: case DIGIT_7:
      case DIGIT_8: case DIGIT_9: case DIGIT_A: case DIGIT_B:
      case DIGIT_C: case DIGIT_D: case DIGIT_E: case DIGIT_F:
      case SIGN_E:
      case DEC_POINT:
      case SIGN_POINT_E:
        input(cmd);
        return;

      case ENTER:
        if (inputInProgress) {
          parseInput();
          return;
        }
        break; // Fall-through to ENTER command below

      case CLEAR:
        if (inputInProgress) {
          input(cmd);
          return;
        }
        break; // Fall-through to CLEAR command below

      case MONITOR_ENTER:
        if (inputInProgress) // Do implicit enter
          parseInput();
        isInsideMonitor = true;
        repaintAll();
        return;

      case MONITOR_EXIT:
        isInsideMonitor = false;
        repaintAll();
        return;

      case MONITOR_UP:
        setMonitorY(monitorY-1,true);
        return;

      case MONITOR_DOWN:
        setMonitorY(monitorY+1,true);
        return;

      case MONITOR_LEFT:
        if (monitorMode == MONITOR_PROG) // page up
          setMonitorY(monitorY-maxMonitorDisplaySize+1, false);
        else
          setMonitorX(monitorX-1,true);
        return;

      case MONITOR_RIGHT:
        if (monitorMode == MONITOR_PROG) // page down
          setMonitorY(monitorY+maxMonitorDisplaySize-1, false);
        else
          setMonitorX(monitorX+1,true);
        return;

      case MONITOR_PUSH:
        if (monitorMode == MONITOR_PROG) { // label (middle): SST 
          if (monitorY < maxMonitorSize-1) {
            progRecording = false;
            progRunning = true;
            singleStepProgram( monitorY );
            progRunning = false;
            progRecording = true;
            setMonitorY(monitorY+1, false);
          }
          return;
        }
        // no break here
      case MONITOR_PUT:
        switch (monitorMode) {
          case MONITOR_MEM:     command(STO,monitorY);         break;
          case MONITOR_STAT:    command(STAT_STO,monitorY);    break;
          case MONITOR_FINANCE: command(FINANCE_STO,monitorY); break;
          case MONITOR_MATRIX:
            if (getMatrix(stack[0]) != null) { // Cannot store Matrix in matrix
              if (cmd == MONITOR_PUSH)
                command(MONITOR_EXIT,0);
              return;
            }
            command(MATRIX_STO,(monitorY<<16)+monitorX);
            break;
          case MONITOR_PROG:                   // label: reset
            progCounter = 0;                   // delete programm
            updateProgMonitor( true );
            return;
        }
        setMonitorY(monitorY+1,true); // Proceed to next element
        if (cmd == MONITOR_PUSH) {
          // Put, pop and return
          command(CLEAR,0);
          command(MONITOR_EXIT,0);
        }
        return;

      case MONITOR_GET:
        switch (monitorMode) {
          case MONITOR_MEM:     command(RCL,monitorY);         break;
          case MONITOR_STAT:    command(STAT_RCL,monitorY);    break;
          case MONITOR_FINANCE: command(FINANCE_RCL,monitorY); break;
          case MONITOR_MATRIX:
            command(MATRIX_RCL,(monitorY<<16)+monitorX);
            break;
          case MONITOR_PROG: // delete current line
            // cannot delete end of program mark
            if (monitorY < maxMonitorSize-1) { 
              int a= progLineAddr[monitorY];
              int d= 1;
              if ( (prog[currentProg][a] & 0x8000) != 0)
                d = 6;
              progCounter -= d;
              System.arraycopy(prog[currentProg],a+d,prog[currentProg],a,
                               progCounter-a);
              updateProgMonitor(false);
              if (monitorYOff+Math.min(maxMonitorDisplaySize-1,monitorSize)
                  >maxMonitorSize) {
                monitorYOff = maxMonitorSize-Math.min(maxMonitorDisplaySize-1,
                                                      monitorSize);
                repaintAll();
              }
            }
            return;
        }
        setMonitorY(monitorY+1,true); // Proceed to next element
        return;
    }

    // For all the commands below, do implicit enter
    if (inputInProgress)
      parseInput();

    if (progRecording)
      record(cmd, param);

    switch (cmd)
    {
      case ENTER:
        enter();
        break;

      case CLEAR:
      case ADD:   case SUB:   case MUL:   case DIV:
      case PERCENT_CHG:
      case YPOWX: case XRTY:
      case PYX:   case CYX:
      case ATAN2: case HYPOT:
      case AND:   case OR:    case XOR:   case BIC:
      case YUPX:  case YDNX:
      case FINANCE_MULINT:    case FINANCE_DIVINT:
      case DHMS_PLUS:
      case TO_CPLX:
      case MOD:   case DIVF:
      case MIN:   case MAX:
      case MATRIX_NEW: case MATRIX_CONCAT:case MATRIX_STACK:
      case MATRIX_AIJ:
        binary(cmd);
        break;

      case NEG:   case RECIP: case SQR:   case ABS:
      case TRANSP:case TRANSP_CONJ:       case DETERM:case TRACE:
      case SQRT:  case PERCENT:
      case CPLX_ARG: case CPLX_CONJ:
      case LN:    case EXP:
      case LOG10: case EXP10: case LOG2:  case EXP2:
      case SIN:   case COS:   case TAN:
      case SINH:  case COSH:  case TANH:
      case ASIN:  case ACOS:  case ATAN:
      case ASINH: case ACOSH: case ATANH:
      case ROUND: case CEIL:  case FLOOR: case TRUNC: case FRAC:
      case XCHGMEM:
        unaryComplexMatrix(cmd,param);
        break;

      case FACT:  case GAMMA:
      case ERFC:  case INVERFC: case PHI: case INVPHI:
      case NOT:
      case TO_DEG:case TO_RAD:case TO_DHMS:case TO_H:
      case SGN:
      case DHMS_TO_UNIX: case UNIX_TO_DHMS:
      case DHMS_TO_JD:   case JD_TO_DHMS:
      case DHMS_TO_MJD:  case MJD_TO_DHMS:
      case LIN_YEST: case LIN_XEST: case LOG_YEST: case LOG_XEST:
      case EXP_YEST: case EXP_XEST: case POW_YEST: case POW_XEST:
      case CONV_C_F: case CONV_F_C:
        unary(cmd);
        break;

      case PI:          push(Real.PI,    null);                break;
      case CONST_c:     push(0x4000001c, 0x4779e12800000000L); break;
      case CONST_h:     push(0x3fffff91, 0x6e182e8b16bd5f42L); break;
      case CONST_mu_0:  push(0x3fffffec, 0x5454dc3e67db2c21L); break;
      case CONST_eps_0: push(0x3fffffdb, 0x4de1dbc537b4c1b4L); break;
      case CONST_NA:    push(0x4000004e, 0x7f86183045affe27L); break;
      case CONST_R:     push(0x40000003, 0x428409e55c0fcb4fL); break;
      case CONST_k:     push(0x3fffffb4, 0x42c3a0166b61ae01L); break;
      case CONST_F:     push(0x40000010, 0x5e3955a6b50b0f28L); break;
      case CONST_alpha: push(0x3ffffff8, 0x778f50a81fcfba71L); break;
      case CONST_a_0:   push(0x3fffffdd, 0x745e07537412adf4L); break;
      case CONST_R_inf: push(0x40000017, 0x53b911c8c56d5cfbL); break;
      case CONST_mu_B:  push(0x3fffffb3, 0x59b155d92797eb1eL); break;
      case CONST_e:     push(0x3fffffc1, 0x5e93683d3137633fL); break;
      case CONST_m_e:   push(0x3fffff9c, 0x49e7728ced335c92L); break;
      case CONST_m_p:   push(0x3fffffa7, 0x42426639a512e22fL); break;
      case CONST_m_n:   push(0x3fffffa7, 0x4259c7d3bd6cba4fL); break;
      case CONST_m_u:   push(0x3fffffa7, 0x41c7dd5a667f9950L); break;
      case CONST_G:     push(0x3fffffde, 0x496233f0f7af9494L); break;
      case CONST_g_n:   push(0x40000003, 0x4e7404ea4a8c154dL); break;
      case CONST_ly:    push(0x40000035, 0x4338f7ee448d8000L); break;
      case CONST_AU:    push(0x40000025, 0x45a974b4c6000000L); break;
      case CONST_pc:    push(0x40000036, 0x6da012f9404b0988L); break;
      case CONST_km_mi: push(0x40000000, 0x66ff7dfa00e27e0fL); break;
      case CONST_m_ft:  push(0x3ffffffe, 0x4e075f6fd21ff2e5L); break;
      case CONST_cm_in: push(0x40000001, 0x5147ae147ae147aeL); break;
      case CONST_km_nm: push(0x40000000, 0x76872b020c49ba5eL); break;
      case CONST_m_yd:  push(0x3fffffff, 0x750b0f27bb2fec57L); break;
      case CONST_g_oz:  push(0x40000004, 0x7165e963dc486ad3L); break;
      case CONST_kg_lb: push(0x3ffffffe, 0x741ea12add794261L); break;
      case CONST_mg_gr: push(0x40000006, 0x40cc855da272862fL); break;
      case CONST_kg_ton:push(0x40000009, 0x7165e963dc486ad3L); break;
      case CONST_J_cal: push(0x40000002, 0x42fd21ff2e48e8a7L); break;
      case CONST_J_Btu: push(0x4000000a, 0x41f0f5c28f5c28f6L); break;
      case CONST_W_hp:  push(0x40000009, 0x5d36666666666666L); break;
      case CONST_l_pt:  push(0x3ffffffe, 0x792217e4c58958fcL); break;
      case CONST_l_cup: push(0x3ffffffd, 0x792217e4c58958fcL); break;
      case CONST_l_gal: push(0x40000001, 0x792217e4c58958fcL); break;
      case CONST_ml_floz:push(0x40000004,0x764b4b5568e820e6L); break;
      case CONST_K_C:   push(0x40000008, 0x444999999999999aL); break;
      case PUSH_ZERO:   push(Real.ZERO,  null);                break;
      case PUSH_ZERO_N: push(Real.ZERO_N,null);                break;
      case PUSH_INF:    push(Real.INF,   null);                break;
      case PUSH_INF_N:  push(Real.INF_N, null);                break;

      case RANDOM:
        rTmp.random();
        push(rTmp,null);
        break;

      case TIME:
        rTmp.time();
        push(rTmp,null);
        break;

      case DATE:
        rTmp.date();
        push(rTmp,null);
        break;

      case TIME_NOW:
        rTmp.time();
        rTmp2.date();
        rTmp.add(rTmp2);
        push(rTmp,null);
        break;

      case GUESS:
        push(stack[0],stackI[0]);
        Guess g = new Guess();
        setMessage("Guess", g.guess(stack[0],stackI[0]));
        break;

      case IF_EQUAL:
      case IF_NEQUAL:
      case IF_LESS:
      case IF_LEQUAL:
      case IF_GREATER:
        cond(cmd);
        break;

      case SELECT:
        trinary(cmd);
        break;

      case RP:
      case PR:
      case MATRIX_SPLIT:
        xyOp(cmd);
        break;

      case CLS:
        lastx.assign(stack[0]);
        lasty.makeZero(); // Clear this in case it is a Matrix reference
        lastz.makeZero(); // ...and this
        lastxi.assign(stackI[0]);
        clearStack();
        undoOp = UNDO_NONE; // Cannot undo this
        break;

      case RCLST:
        push(stack[param],stackI[param]);
        break;

      case ROLLDN:
        rollDown(false);
        undoOp = UNDO_ROLLDN;
        break;

      case ROLLUP:
        rollUp(false);
        undoOp = UNDO_ROLLUP;
        break;

      case XCHG:
        param = 1;
        // fall-through to next case...
      case XCHGST:
        if (param != 0 && stackStr[param] != empty) {
          undoOp = UNDO_XCHGST;
          undoStackEmpty = param; // Using this otherwise unused variable
          xchgSt(param);
        }
        break;

      case LASTX:
        push(lastx,lastxi);
        break;

      case UNDO:
        undo();
        break;

      case STO_X:
        param = stack[0].toInteger();
        if (param<0 || param>15)
          break;
        // fall-through to next case...
      case STO:
        i = cmd==STO ? 0 : 1;
        allocMem();
        mem[param].assign(stack[i]);
        memI[param].assign(stackI[i]);
        if (monitorMode == MONITOR_MEM) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;

      case STP_X:
        param = stack[0].toInteger();
        if (param<0 || param>15)
          break;
        // fall-through to next case...
      case STP:
        i = cmd==STP ? 0 : 1;
        allocMem();
        Matrix X = getMatrix(stack[i]);
        Matrix Y = getMatrix(mem[param]);
        if (X != null || Y != null) {
          if (X != null && Y != null) {
            Y = Matrix.add(Y,X);
            linkToMatrix(mem[param],memI[param],Y);
          } else {
            mem[param].makeNan();
            memI[param].makeZero();
          }
        } else {
          mem[param].add(stack[i]);
          memI[param].add(stackI[i]);
        }
        if (monitorMode == MONITOR_MEM) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;

      case RCL_X:
        param = stack[0].toInteger();
        if (param<0 || param>15)
          break;
        // fall-through to next case...
      case RCL:
        if (mem != null)
          push(mem[param],memI[param]);
        else
          push(Real.ZERO,null);
        break;

      case CLMEM:
        clearMem();
        if (monitorMode == MONITOR_MEM) {
          clearMonitorStrings();
          repaintAll();
        }
        break;

      case SUMPL:
      case SUMMI:
        sum(cmd);
        break;

      case CLST:
        clearStat();
        if (monitorMode == MONITOR_STAT) {
          clearMonitorStrings();
          repaintAll();
        }
        break;

      case AVG:
      case STDEV:
      case PSTDEV:
      case LIN_AB:
      case LOG_AB:
      case EXP_AB:
      case POW_AB:
      case MATRIX_SIZE:
        stat2(cmd);
        break;

      case AVGXW:
      case LIN_R:
      case LOG_R:
      case EXP_R:
      case POW_R:
        stat1(cmd);
        break;

      case STAT_STO:
        allocStat();
        if (getMatrix(stack[0]) == null) // Cannot store Matrix in stat
          stat[param].assign(stack[0]);
        else
          stat[param].makeNan();
        if (monitorMode == MONITOR_STAT) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;

      case STAT_RCL:
        if (stat != null)
          push(stat[param],null);
        else
          push(Real.ZERO,null);
        break;

      case N:         push(SUM1,     null); break;
      case SUMX:      push(SUMx,     null); break;
      case SUMXX:     push(SUMx2,    null); break;
      case SUMY:      push(SUMy,     null); break;
      case SUMYY:     push(SUMy2,    null); break;
      case SUMXY:     push(SUMxy,    null); break;
      case SUMLNX:    push(SUMlnx,   null); break;
      case SUMLN2X:   push(SUMln2x,  null); break;
      case SUMLNY:    push(SUMlny,   null); break;
      case SUMLN2Y:   push(SUMln2y,  null); break;
      case SUMXLNY:   push(SUMxlny,  null); break;
      case SUMYLNX:   push(SUMylnx,  null); break;
      case SUMLNXLNY: push(SUMlnxlny,null); break;

      case FACTORIZE:
        lastx.assign(stack[0]);
        lasty.makeZero(); // Clear this in case it is a Matrix reference
        lastz.makeZero(); // ...and this
        lastxi.assign(stackI[0]);
        stack[0].round();
        if (stack[0].exponent > 0x4000001e || !stack[0].isFinite() ||
            !stackI[0].isZero())
        {
          setMessage("Factorize","Argument must not be abnormal, complex or "+
                     "greater than 2^31-1.");
          push(Real.NAN,null);
        } else {
          int a = stack[0].toInteger();
          int b = greatestFactor(a);
          rollUp(true);
          lasty.assign(stack[0]);
          lastyi.assign(stackI[0]);
          undoStackEmpty = stackStr[0]==empty ? stackStr[1]==empty ? 2 : 1 : 0;
          stack[0].assign((b!=0) ? a/b : 0);
          stack[1].assign(b);
          stackI[0].makeZero();
          stackI[1].makeZero();
          stackStr[0] = null;
          stackStr[1] = null;
        }
        undoOp = UNDO_PUSHXY;
        break;

      case CPLX_SPLIT:
        lastx.assign(stack[0]);
        lastxi.assign(stackI[0]);
        rollUp(true);
        lasty.assign(stack[0]);
        lastyi.assign(stackI[0]);
        stack[0].assign(stack[1]);
        stack[1].assign(stackI[1]);
        stackI[0].makeZero();
        stackI[1].makeZero();
        lastz.makeZero(); // Clear this in case it is a Matrix reference
        undoStackEmpty = stackStr[0]==empty ? stackStr[1]==empty ? 2 : 1 : 0;
        stackStr[0] = null;
        stackStr[1] = null;
        undoOp = UNDO_PUSHXY;
        break;

      case FINANCE_STO:
        allocFinance();
        if (getMatrix(stack[0]) == null) // Cannot store Matrix in finance
          finance[param].assign(stack[0]);
        else
          finance[param].makeNan();
        if (monitorMode == MONITOR_FINANCE) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;

      case FINANCE_RCL:
        if (finance != null)
          push(finance[param],null);
        else
          push(Real.ZERO,null);
        break;

      case FINANCE_SOLVE:
        financeSolve(param);
        if (monitorMode == MONITOR_FINANCE) {
          monitorStr[param] = null;
          repaintAll();
        }
        break;

      case FINANCE_CLEAR:
        clearFinance();
        if (monitorMode == MONITOR_FINANCE) {
          clearMonitorStrings();
          repaintAll();
        }
        break;

      case FINANCE_BGNEND:
        begin = !begin;
        break;

      case MONITOR_NONE:
        setMonitoring(cmd,0,0,null,null,null);
        break;

      case MONITOR_MEM:
        setMonitoring(cmd,param,MEM_SIZE,mem,memI,memLabels);
        break;

      case MONITOR_STAT:
        setMonitoring(cmd,param,STAT_SIZE,stat,null,statLabels);
        break;

      case MONITOR_FINANCE:
        setMonitoring(cmd,FINANCE_SIZE,FINANCE_SIZE,finance,null,
                      financeLabels);
        break;

      case MONITOR_MATRIX:
        setMonitoring(cmd,param,0,null,null,null);
        monitoredMatrix = null;
        // updateMatrixMonitor() will be run later
        break;

      case MONITOR_PROG:
        if (!progRecording)
          break;             // what happened here? Should not happen!
        progInitialMonitorSize = param; // also for size = 0
        setMonitoring(MONITOR_PROG,param,0,null,null,null);
        updateProgMonitor( true );
        break;

      case MATRIX_STO:
        Matrix M = getCurrentMatrix();
        if (M == null)
          break;
        int col = param&0xffff;
        int row = (param>>16)&0xffff;
        if (col>=M.cols || row>=M.rows)
          break;
        matrixGC();
        if (M.refCount > 1) { // "Copy on write"
          M = new Matrix(M);
          // Find original matrix position, and automagically replace it
          for (i=0; i<STACK_SIZE; i++)
            if (getMatrix(stack[i]) != null) {
              linkToMatrix(stack[i],stackI[i],M);
              break;
            }
        }
        if (getMatrix(stack[0]) == null) // Cannot store Matrix in matrix
          M.setElement(row,col,stack[0],stackI[0]);
        else
          M.setElement(row,col,Real.NAN,null);
        if (monitorMode == MONITOR_MATRIX) {
          if (row<maxMonitorSize)
            monitorStr[row] = null;
          if (imagMonitors == null && M.DI != null)
            imagMonitors = M.DI[col];
          repaintAll();
        }
        break;

      case MATRIX_RCL:
        M = getCurrentMatrix();
        if (M == null) {
          push(Real.NAN,null);
        } else {
          col = param&0xffff;
          row = (param>>16)&0xffff;
          M.getElement(row,col,rTmp,rTmp2);
          push(rTmp,rTmp2);
        }
        break;

      case NORM:
        if (format.fse != Real.NumberFormat.FSE_NONE) {
          format.fse = Real.NumberFormat.FSE_NONE;
          clearStrings();
        }
        break;

      case FIX:
        if (format.fse != Real.NumberFormat.FSE_FIX ||
            format.precision != param)
        {
          format.fse = Real.NumberFormat.FSE_FIX;
          format.precision = param;
          clearStrings();
        }
        break;

      case SCI:
        if (format.fse != Real.NumberFormat.FSE_SCI ||
            format.precision != param)
        {
          format.fse = Real.NumberFormat.FSE_SCI;
          format.precision = param;
          clearStrings();
        }
        break;

      case ENG:
        if (format.fse != Real.NumberFormat.FSE_ENG ||
            format.precision != param)
        {
          format.fse = Real.NumberFormat.FSE_ENG;
          format.precision = param;
          clearStrings();
        }
        break;

      case POINT_DOT:
        if (format.point != '.') {
          format.point = '.';
          if (format.thousand == '.')
            format.thousand = ',';
          clearStrings();
        }
        break;

      case POINT_COMMA:
        if (format.point != ',') {
          format.point = ',';
          if (format.thousand == ',')
            format.thousand = '.';
          clearStrings();
        }
        break;

      case POINT_REMOVE:
        if (!format.removePoint) {
          format.removePoint = true;
          clearStrings();
        }
        break;

      case POINT_KEEP:
        if (format.removePoint) {
          format.removePoint = false;
          clearStrings();
        }
        break;

      case THOUSAND_DOT:
        if (format.thousand != '.' && format.thousand != ',') {
          format.thousand = (format.point=='.') ? ',' : '.';
          clearStrings();
        }
        break;

      case THOUSAND_SPACE:
        if (format.thousand != ' ') {
          format.thousand = ' ';
          clearStrings();
        }
        break;

      case THOUSAND_QUOTE:
        if (format.thousand != '\'') {
          format.thousand = '\'';
          clearStrings();
        }
        break;

      case THOUSAND_NONE:
        if (format.thousand != 0) {
          format.thousand = 0;
          clearStrings();
        }
        break;

      case BASE_BIN:
        if (format.base != 2) {
          format.base = 2;
          clearStrings();
        }
        break;

      case BASE_OCT:
        if (format.base != 8) {
          format.base = 8;
          clearStrings();
        }
        break;

      case BASE_DEC:
        if (format.base != 10) {
          format.base = 10;
          clearStrings();
        }
        break;

      case BASE_HEX:
        if (format.base != 16) {
          format.base = 16;
          clearStrings();
        }
        break;

      case TRIG_DEGRAD:
        degrees = !degrees;
        break;

      case FREE_MEM:
        rollUp(true);
        rollUp(true);
        Runtime.getRuntime().gc();
        stack[0].assign(Runtime.getRuntime().freeMemory());
        stack[1].assign(Runtime.getRuntime().totalMemory());
        stackI[0].makeZero();
        stackI[1].makeZero();
        stackStr[0] = null;
        stackStr[1] = null;
        break;

      case PROG_EDIT:
        progRecording = true;
        currentProg = param;
        matrixGC();
        if (prog[currentProg] == null || prog[currentProg].length == 0) {
          prog[currentProg] = new short[10];
          progCounter = 0;
        } else {
          progCounter = prog[currentProg].length;
        } 
        if (progInitialMonitorSize > 0) {
          setMonitoring(MONITOR_PROG,progInitialMonitorSize,0,null,null,null);
          updateProgMonitor( true );
        }
        break;

      case FINALIZE:
      case PROG_FINISH:
        if (progRecording && prog[currentProg]!=null) {
          progRecording = false;
          matrixGC();
          short [] prog2 = new short[progCounter];
          System.arraycopy(prog[currentProg],0,prog2,0,progCounter);
          prog[currentProg] = prog2;
          if (monitorMode == MONITOR_PROG) 
            setMonitoring(MONITOR_NONE,0,0,null,null,null);
        }
        break;

      case PROG_RUN:
        currentProg = param;
        if (prog[currentProg] != null) {
          progRunning = true;
          progCounter = 0;
          executeProgram();
          progRunning = false;
        }
        break;

      case PROG_CLEAR:
        currentProg = param;
        prog[currentProg] = null;
        progLabels[currentProg] = emptyProg;
        progRecording = false;
        progRunning = false;
        break;

      case PROG_DIFF:
        currentProg = param;
        if (prog[currentProg] != null) {
          progRunning = true;
          differentiateProgram();
          progRunning = false;
        }
        break;
    }

    // Do this here, because almost *anything* can change current matrix
    if (monitorMode == MONITOR_MATRIX)
      updateMatrixMonitor();
  }


  // Create an inner class to limit each class size to 64kB
  // This is a temporary solution
  protected class CalcEngineInner {


  int graphCmd;
  Real xMin,xMax,yMin,yMax,a,b,c,bi,x0,x0i,y0,y1,y2,y0i,y1i,y2i,total,totalI;
  long integralN,totalExtra,totalExtraI;
  int integralDepth;
  boolean integralFailed;
  boolean maximizing;

  public boolean prepareGraph(int cmd, int param) {
    graphCmd = cmd;
    progRunning = false;

    if (cmd >= PROG_DRAW) {
      if (param<0 || param>=NUM_PROGS || prog[param] == null) {
        setMessage("Draw", "The selected program is empty");
        return false;
      }
      currentProg = param;
    } else if (stat == null || statLogSize == 0) {
      setMessage("Draw", "The statistics are empty");
      return false;
    }

    xMin = new Real();
    xMax = new Real();
    yMin = new Real();
    yMax = new Real();
    a = new Real();
    b = new Real();
    Real x = rTmp3;
    Real y = rTmp4;
    int i;

    // Find boundaries
    if (cmd >= PROG_DRAW && cmd <= PROG_DRAWPARM) {
      if (inputInProgress)
        parseInput();
      xMin.assign(stack[3]);
      xMax.assign(stack[2]);
      yMin.assign(stack[1]);
      yMax.assign(stack[0]);
      if (getStackHeight()<4 ||
          xMin.greaterEqual(xMax) || yMin.greaterEqual(yMax)) {
        setMessage("Draw", "The draw area limits, xMin, xMax, yMin and yMax "+
                   "must be pushed to the stack (in that order) before "+
                   "drawing. xMin must be less than xMax and yMin must be "+
                   "less than yMax.");
        return false;
      }
    }
    else if (cmd == PROG_SOLVE)
    {
      if (getStackHeight()<2) {
        setMessage("Solve", "The search bounds, a and b, must be pushed to"+
                   " the stack before solving. The search bounds must"+
                   " straddle the root, i.e. either f(a) or f(b) must be"+
                   " negative, but not both");
        return false;
      }
      y1 = new Real();
      y2 = new Real();

      // Dummy limits for painting a nice progress bar
      xMin.assign(1);
      xMax.assign(2);
      yMin.assign(-1);
      yMax.assign(1);

      // Fetch solve interval [a, b]
      a.assign(stack[1]);
      b.assign(stack[0]);
      push(Real.NAN,null);

      if (!a.isFinite() || !b.isFinite() ||
          !stackI[1].isZero() || !stackI[2].isZero()) {
        // Abnormal limits. (nan is already pushed)
        setMessage("Solve", "Abnormal or complex search bounds");
        return false;
      }
      Real.magicRounding = false;

      if (a.sign != b.sign) {
        // Check first the pathological case f(0)==0
        stack[0].makeZero();
        executeProgram();
        y1.assign(stack[0]);
        if (!y1.isFinite() || !stackI[0].isZero()) {
          // Discontinuous or complex function
          stack[0].makeNan();
          stackI[0].makeZero();
          Real.magicRounding = true;
          setMessage("Solve", "Discontinuous or complex function");
          return false;
        }
        if (y1.isZero()) {
          Real.magicRounding = true;
          return false; // We're done; f(0)=0, and stack[0] contains 0
        }
      }

      // Evaluate function at limits
      stack[0].assign(a);
      executeProgram();
      y1.assign(stack[0]);
      if (!y1.isFinite() || !stackI[0].isZero()) {
        // Discontinuous or complex function
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        setMessage("Solve", "Discontinuous or complex function");
        return false;
      }
      if (y1.isZero()) {
        stack[0].assign(a);
        Real.magicRounding = true;
        return false;
      }

      stack[0].assign(b);
      executeProgram();
      y2.assign(stack[0]);
      if (!y2.isFinite() || !stackI[0].isZero() || y1.sign == y2.sign) {
        // Discontinuous or complex function, or
        // initial bounds do not straddle the root
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        if (y1.sign == y2.sign)
          setMessage("Solve", "The search bounds, a and b, must be pushed to"+
                     " the stack before solving. The search bounds must"+
                     " straddle the root, i.e. either f(a) or f(b) must be"+
                     " negative, but not both");
        else
          setMessage("Solve", "Discontinuous or complex function");
        return false;
      }
      if (y2.isZero()) {
        stack[0].assign(b);
        Real.magicRounding = true;
        return false;
      }

      // Let stack always hold best value till now
      stack[0].assign(y1.absLessThan(y2) ? a : b);
      Real.magicRounding = true;
    }
    else if (cmd == PROG_INTEGR)
    {
      if (getStackHeight()<3 || stack[0].isZero()) {
        setMessage("Integrate", "The integration limits and the desired"+
                   " accuracy (i.e. some small nonzero number) must be pushed"+
                   " to the stack in that order before integrating");
        return false;
      }
      total  = new Real();
      totalI = new Real();
      y0  = new Real();
      y0i = new Real();
      y1  = new Real();
      y1i = new Real();
      y2  = new Real();
      y2i = new Real();
      x0  = new Real();
      x0i = new Real();
      bi  = new Real();

      totalExtra = 0;
      totalExtraI = 0;
      integralN = 0;
      integralDepth = 0;
      integralFailed = false;
      y0.makeNan();

      x0.assign(stack[2]);
      x0i.assign(stackI[2]);
      b.assign(stack[1]);
      bi.assign(stackI[1]);
      b.sub(x0);
      bi.sub(x0i);
      a.assign(stack[0]); // Error term

      xMin.assign(1);
      xMax.assign(2);
      yMin.assign(-2);
      yMax.assign(1);

      push(Real.ZERO,null);
    }
    else if (cmd == PROG_MINMAX)
    {
      if (getStackHeight()<2) {
        setMessage("Min/max", "The search bounds, a and b, must be pushed to"+
                   " the stack before activating this function. The search"+
                   " bounds must be set so that the function value at the"+
                   " midpoint, f((a+b)/2), is either greater than both"+
                   " f(a) and f(b), or less than both f(a) and f(b).");
        return false;
      }
      c = new Real();
      y0 = new Real();
      y1 = new Real();
      y2 = new Real();

      // Dummy limits for painting a nice progress bar
      xMin.assign(1);
      xMax.assign(2);
      yMin.assign(-1);
      yMax.assign(1);

      Real.magicRounding = false;

      // Fetch min/max interval [a, b]
      a.assign(stack[1]);
      c.assign(stack[0]);
      push(Real.NAN,null);
      b.assign(a);
      b.add(c);
      b.scalbn(-1);

      if (!a.isFinite() || !c.isFinite() ||
          !stackI[1].isZero() || !stackI[2].isZero()) {
        // Abnormal limits. (nan is already pushed)
        Real.magicRounding = true;
        setMessage("Min/max", "Abnormal or complex search bounds");
        return false;
      }

      // Evaluate function at limits
      stack[0].assign(a);
      executeProgram();
      y0.assign(stack[0]);
      if (!y0.isFinite() || !stackI[0].isZero()) {
        // Discontinuous or complex function
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        setMessage("Min/max", "Discontinuous or complex function");
        return false;
      }

      stack[0].assign(b);
      executeProgram();
      y1.assign(stack[0]);
      if (!y1.isFinite() || !stackI[0].isZero()) {
        // Discontinuous or complex function
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        setMessage("Min/max", "Discontinuous or complex function");
        return false;
      }

      stack[0].assign(c);
      executeProgram();
      y2.assign(stack[0]);
      if (!y2.isFinite() || !stackI[0].isZero()) {
        // Discontinuous or complex function
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        setMessage("Min/max", "Discontinuous or complex function");
        return false;
      }

      if ((y1.greaterThan(y0) && y1.greaterEqual(y2)) ||
          (y1.greaterEqual(y0) && y1.greaterThan(y2))) {
        maximizing = true;
      } else if ((y1.lessThan(y0) && y1.lessEqual(y2)) ||
                 (y1.lessEqual(y0) && y1.lessThan(y2))) {
        maximizing = false; // i.e. minimizing
      } else {
        // undecidable max/min condition
        stack[0].makeNan();
        stackI[0].makeZero();
        Real.magicRounding = true;
        setMessage("Min/max", "Undecidable min/max condition. The initial "+
                   "search bounds a and b must be set so that the function "+
                   "value at the midpoint, f((a+b)/2), is either greater than"+
                   " both f(a) and f(b), or less than both f(a) and f(b).");
        return false;
      }

      // Let stack always hold best value till now
      stack[0].assign(b);
      Real.magicRounding = true;
    }
    else // Draw statistics
    {
      for (i=0; i<statLogSize; i++) {
        int index = (statLogStart+i)%STATLOG_SIZE;
        x.assignFloatBits(statLog[index*2]);
        y.assignFloatBits(statLog[index*2+1]);
        if (x.isFiniteNonZero() && y.isFiniteNonZero()) {
          if (x.lessThan(xMin))    xMin.assign(x);
          if (x.greaterThan(xMax)) xMax.assign(x);
          if (y.lessThan(yMin))    yMin.assign(y);
          if (y.greaterThan(yMax)) yMax.assign(y);
        }
      }
      // Expand boundaries by 10%
      rTmp.assign("1.1");
      xMin.mul(rTmp);
      xMax.mul(rTmp);
      yMin.mul(rTmp);
      yMax.mul(rTmp);
      if (xMin.greaterEqual(xMax) || yMin.greaterEqual(yMax)) {
        setMessage("Draw", "Statistics contain only zeros");
        return false;
      }
    }

    progRunning = true;
    return true;
  }

  private int rangeScale(Real x, Real min, Real max, int size, Real offset) {
    if (!x.isFinite())
      return (x.sign*2-1)*0x4000;
    rTmp.assign(x);
    rTmp.sub(min);
    max.sub(min); // May be inexact, yes, but avoids using temporary
    rTmp.div(max);
    max.add(min);
    rTmp.mul(size-1);
    rTmp.add(offset);
    rTmp.round();
    int i = rTmp.toInteger();
    return (i < -0x4000) ? -0x4000 : ((i > 0x4000) ? 0x4000 : i);
  }

  private int findTickStep(Real step, Real fac, Real min, Real max, int size) {
    int pow,bigTickInterval;
    rTmp.assign(max);
    rTmp.sub(min);
    rTmp.mul(11); // minimum tick distance, 11 pixels
    rTmp.div(size); // range of 11 pixels on screen
    step.assign(rTmp);
    pow = step.lowPow10(); // convert to lower power of 10
    rTmp.div(step);

    if (rTmp.lessThan(2)) {
      // Minor 2, major 10
      step.mul(2);
      bigTickInterval = 5;
      if (pow > 0)
        pow--; // preserve tick labels 1000,2000,...
    } else if (rTmp.lessThan(5)) {
      // Minor 5, major 10
      step.mul(5);
      bigTickInterval = 2;
    } else {
      // Minor 1, major 5
      step.mul(10);
      bigTickInterval = 5;
      if (pow < 0)
        pow++; // preserve tick labels 0.05,0.1,...
    }

    if (pow > 0)
      pow = ((pow+1)/3)*3;
    else
      pow = (pow/3)*3;
    fac.assign(Real.TEN);
    fac.pow(pow);

    return bigTickInterval;

    // Sample ticks              step pow  fac
    //
    //  -10-20-30-40             5e-3  -3 1e-3
    //  ----0.05----0.1----0.15  0.01  -3    1
    //  ----0.1----0.2----0.3    0.02  -2    1
    //  -0.1-0.2-0.3-0.4         0.05  -2    1
    //  ----0.5----1----1.5       0.1  -2    1
    //  ----1----2----3----4      0.2  -1    1
    //  -1-2-3-4                  0.5  -1    1
    //  ----5----10----15           1  -1    1
    //  ----10----20----30          2   0    1
    //  -10-20-30-40                5   0    1
    //  ----50----100----150       10   0    1
    //  ----100----200----300      20   1    1
    //  -100-200-300-400           50   1    1
    //  ----500----1000----1500   100   1    1
    //  ----1000----2000----3000  200   2    1
    //  -1-2-3-4                  500   2  1e3
  }

  public void startGraph(Graphics g, int gx, int gy, int gw, int gh,
                         boolean bgrDisplay)
  {
    int i,xi,yi,pyi,inc,bigTick,lx,ly;
    Real h = rTmp2;
    Real x = rTmp3;
    Real y = rTmp4;
    Real fac = new Real();
    Real tmp = new Real();
    GFont font = new GFont(GFont.SMALL | (bgrDisplay ? GFont.BGR_ORDER : 0));
    int fh = font.getHeight()-1;
    int fw = font.charWidth();
    Real.NumberFormat fmt = new Real.NumberFormat();
    fmt.point = format.point;

    g.setClip(gx,gy,gw,gh);
    // shrink window by 4 pixels
    gx += 2;
    gy += 2;
    gw -= 4;
    gh -= 4;

    // Draw X axis
    g.setColor(0,255,128);
    yi = gy+rangeScale(Real.ZERO,yMax,yMin,gh,Real.ZERO);
    g.drawLine(gx-2,yi,gx+gw+1,yi);
    bigTick = findTickStep(h,fac,xMin,xMax,gw);
    x.assign(xMin);
    x.div(h);
    x.ceil();
    i = x.toInteger();
    x.mul(h);
    h.scalbn(-1);
    y.assign(h);
    y.add(xMax);
    while (x.lessThan(y)) {
      if (!x.absLessThan(h)) {
        xi = gx+rangeScale(x,xMin,xMax,gw,Real.ZERO);
        g.setColor(0,32,16);
        g.drawLine(xi,gy-2,xi,gy+gh+1);

        if (i%bigTick == 0 && graphCmd<PROG_SOLVE)
        {
          tmp.assign(x);
          tmp.div(fac);
          String label = tmp.toString(fmt);
          lx = xi-fw*label.length()/2;
          if (lx < gx-2)
            lx = gx-2;
          if (lx > gx+gw+2-fw*label.length())
            lx = gx+gw+2-fw*label.length();

          if ((yi>=gy+gh/2 && yi+4+fh <= gy+gh+1) || yi-3-fh<gy-1)
            ly = yi+4;
          else
            ly = yi-3-fh;

          if (ly<gy-1)
            ly = gy-1;
          if (ly > gy+gh+1-fh)
            ly = gy+gh+1-fh;
          font.drawString(g,lx,ly,label);
        }
        inc = (i%bigTick == 0) ? 2 : 1;
        g.setColor(0,255,128);
        g.drawLine(xi,yi-inc,xi,yi+inc);
      }
      x.add(h);
      x.add(h);
      i++;
    }

    // Draw Y axis
    xi = gx+rangeScale(Real.ZERO,xMin,xMax,gw,Real.ZERO);
    g.drawLine(xi,gy-2,xi,gy+gh+1);

    if (graphCmd==PROG_SOLVE || graphCmd==PROG_INTEGR || graphCmd==PROG_MINMAX)
      return; // Return now to continue later

    bigTick = findTickStep(h,fac,yMin,yMax,gh);
    y.assign(yMin);
    y.div(h);
    y.ceil();
    i = y.toInteger();
    y.mul(h);
    h.scalbn(-1);
    x.assign(h);
    x.add(yMax);
    boolean sideSelected = false;
    boolean rightSide = false;
    while (y.lessThan(x)) {
      if (!y.absLessThan(h)) {
        yi = gy+rangeScale(y,yMax,yMin,gh,Real.ZERO);
        g.setColor(0,32,16);
        g.drawLine(gx-2,yi,gx+gw+1,yi);

        if (i%bigTick == 0)
        {
          tmp.assign(y);
          tmp.div(fac);
          String label = tmp.toString(fmt);
          ly = yi-fh/2;
          if (ly < gy-1)
            ly = gy-1;
          if (ly > gy+gh+1-fh)
            ly = gy+gh+1-fh;

          if ((sideSelected && rightSide) ||
              (!sideSelected &&
               ((xi>gx+gw/2 && xi+4+fw*label.length() <= gx+gw+2) ||
                xi-3-fw*label.length()<gx-2)))
          {
            rightSide = true;
            lx = xi+4;
          } else {
            lx = xi-3-fw*label.length();
          }
          sideSelected = true; // Keep to one side of axis once it is selected

          if (lx < gx-2)
            lx = gx-2;
          if (lx > gx+gw+2-fw*label.length())
            lx = gx+gw+2-fw*label.length();
          font.drawString(g,lx,ly,label);
        }
        inc = (i%bigTick == 0) ? 2 : 1;
        g.setColor(0,255,128);
        g.drawLine(xi-inc,yi,xi+inc,yi);
      }
      y.add(h);
      y.add(h);
      i++;
    }

    if (graphCmd >= PROG_DRAW && graphCmd <= PROG_DRAWPARM) {
      // Return now to continue drawing graph indefinitely
      a.assign(0, 0x3fffffff, 0x4f1bbcdcbfa53e0bL); // a = golden ratio, 0.618
      b.makeZero();
      return;
    }

    // Draw statistics graph
    g.setColor(255,0,128);

    switch (graphCmd) {
      case LIN_DRAW:
        statAB(a,b,SUMx,SUMx2,SUMy,SUMy2,SUMxy);
        break;
      case LOG_DRAW:
        statAB(a,b,SUMlnx,SUMln2x,SUMy,SUMy2,SUMylnx);
        break;
      case EXP_DRAW:
        statAB(a,b,SUMx,SUMx2,SUMlny,SUMln2y,SUMxlny);
        break;
      case POW_DRAW:
        statAB(a,b,SUMlnx,SUMln2x,SUMlny,SUMln2y,SUMlnxlny);
        break;
    }
    if (graphCmd != AVG_DRAW && a.isFinite() && b.isFinite()) {
      pyi = -1000;
      inc = (graphCmd==LIN_DRAW) ? gw-1 : 5;
      for (xi=0; xi<gw+5; xi+=inc) {
        x.assign(xi);
        x.div(gw-1);
        rTmp.assign(xMax);
        rTmp.sub(xMin);
        x.mul(rTmp);
        x.add(xMin);
        if (graphCmd==LOG_DRAW || graphCmd==POW_DRAW)
          x.ln();
        y.assign(x);
        y.mul(a);
        y.add(b);
        if (graphCmd==EXP_DRAW || graphCmd==POW_DRAW)
          y.exp();
        yi = rangeScale(y,yMax,yMin,gh,Real.HALF);
        if (yi > -1000 && yi<1000+gh) {
          if (pyi > -1000) {
            g.drawLine(xi-inc+gx,pyi+gy,xi+gx,yi+gy);
            g.drawLine(xi-inc+gx,pyi-1+gy,xi+gx,yi-1+gy);
          }
          pyi = yi;
        } else {
          if (pyi > -1000)
            break; // We have drawn from inside to outside
          pyi = -1000;
        }
      }
    }

    // Draw points
    g.setColor(255,255,255);
    for (i=0; i<statLogSize; i++) {
      int index = (statLogStart+i)%STATLOG_SIZE;
      x.assignFloatBits(statLog[index*2]);
      y.assignFloatBits(statLog[index*2+1]);
      if (x.isFinite() && y.isFinite()) {
        xi = gx+rangeScale(x,xMin,xMax,gw,Real.ZERO);
        yi = gy+rangeScale(y,yMax,yMin,gh,Real.ZERO);
        g.drawLine(xi,yi-1,xi,yi+1);
        g.drawLine(xi-1,yi,xi+1,yi);
      }
    }

    // Draw average
    if (graphCmd == AVG_DRAW) {
      g.setColor(255,0,128);
      x.assign(SUMx);
      x.div(SUM1);
      y.assign(SUMy);
      y.div(SUM1);
      if (x.isFinite() && y.isFinite()) {
        xi = gx+rangeScale(x,xMin,xMax,gw,Real.ZERO);
        yi = gy+rangeScale(y,yMax,yMin,gh,Real.ZERO);
        g.fillRect(xi-1,yi-1,3,3);
      }
    }

    progRunning = false;
  }

  // Alternating bisection and interpolation steps in root finding
  private boolean bisect(Real x1, Real y1, Real x2, Real y2) {
    Real x = xMin; // use xMin as temporary value
    Real y = rTmp;

    // Stop when the interval is 0
    if (x1.equalTo(x2))
      return false;

    for (int i=0; i<2; i++) {
      if (i==0) {
        // bisect
        x.assign(x1);
        x.add(x2);
        x.scalbn(-1);
        if (x.equalTo(x1) || x.equalTo(x2)) {
          // Pathological case may oscillate between two "nextAfter" x-values
          // => root is the x with the smallest y
          if (y2.absLessThan(y1))
            x1.assign(x2);
          return false;
        }
      } else {
        // interpolate
        x.assign(x1);
        x.sub(x2);
        y.assign(y2);
        y.sub(y1);
        x.div(y);
        x.mul(y1);
        x.add(x1);
      }
      stack[0].assign(x);
      executeProgram();
      // Results must always be finite
      y.assign(stack[0]);
      if (!y.isFinite() || !stackI[0].isZero()) {
        // Discontinuous or complex function
        x1.makeNan();
        setMessage("Solve", "Discontinuous or complex function");
        return false;
      }
      if (y.isZero()) { // perhaps we nailed the root?
        x1.assign(x);
        return false;
      }

      if (y1.sign == y.sign) {
        x1.assign(x);
        y1.assign(y);
      } else {
        x2.assign(x);
        y2.assign(y);
      }
    }
    xMin.assign(1); // Restore dummy limit
    return true;
  }

  // Gauss-Legendre Quadrature of order 4
  private void GL4_stripe(Real sum, Real sumI, Real x0, Real x0i,
                          Real H, Real HI, int depth, long n) {
    H.scalbn(-depth);
    HI.scalbn(-depth);
    sum.makeZero();
    sumI.makeZero();
    for (int i=0; i<4; i++) {
      if (i<2)
        stack[0].assign(0, 0x3ffffffe, 0x6e39b6f3d8e61419L);
      else
        stack[0].assign(0, 0x3ffffffd, 0x5708ff6774f7f08aL);
      if ((i&1)!=0)
        stack[0].neg();
      stack[0].add(Real.HALF);
      stackI[0].assign(stack[0]);
      stack[0].mul(H);
      stackI[0].mul(HI);
      rTmp.assign(H);  // Should do this outside loop, but lack temp
      rTmp2.assign(n); // .
      rTmp.mul(rTmp2); // .
      rTmp.add(x0);    // .
      stack[0].add(rTmp);
      rTmp.assign(HI); // Should do this outside loop, but lack temp
      rTmp.mul(rTmp2); // .
      rTmp.add(x0i);   // .
      stackI[0].add(rTmp);

      executeProgram();
      if (!stack[0].isFinite() || !stackI[0].isFinite()) {
        // Discontinuous function
        sum.makeNan();
        sumI.makeZero();
        H.scalbn(depth); // Restore H
        HI.scalbn(depth);
        return;
      }
      if (i<2)
        rTmp.assign(0, 0x3ffffffd, 0x590d03df9ed9ac8dL);
      else
        rTmp.assign(0, 0x3ffffffe, 0x53797e10309329b9L);
      stack[0].mul(rTmp);
      stackI[0].mul(rTmp);

      sum.add(stack[0]);
      sumI.add(stackI[0]);
    }
    Complex.mul(sum,sumI,H,HI);
    H.scalbn(depth); // Restore H
    HI.scalbn(depth);
  }

  public void continueGraph(Graphics g, int gx, int gy, int gw, int gh) {
    long start = System.currentTimeMillis();
    Real x = rTmp3;
    int i,xi=0,yi;

    g.setClip(gx,gy,gw,gh);
    // shrink window by 4 pixels
    gx += 2;
    gy += 2;
    gw -= 4;
    gh -= 4;

    do
    {
      if (graphCmd >= PROG_DRAW && graphCmd <= PROG_DRAWPARM) {
        Real.magicRounding = false;
        b.add(a);
        if (b.greaterThan(Real.ONE))
          b.sub(Real.ONE);
        if (graphCmd == PROG_DRAW) {
          x.assign(xMax);
          x.sub(xMin);
          x.mul(b);
          x.add(xMin);
          xi = gx+rangeScale(x,xMin,xMax,gw,Real.ZERO);
        } else {
          x.assign(b);
          if (graphCmd == PROG_DRAWPOL) {
            if (degrees)
              x.mul(360);
            else
              x.mul(Real.PI2);
            x.mul(Real.TEN); // 10 "rounds"
          }
        }

        push(x,null);
        executeProgram();

        Real y = stack[0];
        Real yimag = stackI[0];

        if (graphCmd == PROG_DRAW) {
          yi = -100;
          if (y.isFinite() && (!y.isZero() || yimag.isZero())) {
            yi = gy+rangeScale(y,yMax,yMin,gh,Real.HALF);
            g.setColor(255,0,128);
            g.drawLine(xi,yi-1,xi,yi);
          }
          if (!yimag.isZero() && yimag.isFinite()) {
            int yi2 = gy+rangeScale(yimag,yMax,yMin,gh,Real.HALF);
            g.setColor(255,255,yi2==yi ? 255 : 0);
            g.drawLine(xi,yi2-1,xi,yi2);
          }
        } else { // PROG_DRAWPOL or PROG_DRAWPARM
          if (graphCmd == PROG_DRAWPOL) {
            x.assign(b);
            x.mul(Real.PI2);
            x.mul(10); // 10 rounds
            rTmp4.assign(x);
            rTmp4.sin();
            x.cos();
            x.mul(y);
            y.mul(rTmp4);
          } else { // PROG_DRAWPARM
            x.assign(y);
            y.assign(yimag);
          }
          if (x.isFinite() && y.isFinite()) {
            yi = gy+rangeScale(y,yMax,yMin,gh,Real.ZERO);
            xi = gx+rangeScale(x,xMin,xMax,gw,Real.ZERO);
            g.setColor(255,0,128);
            g.drawLine(xi,yi,xi,yi);
          }
        }
        command(CLEAR,0); // Remove result from stack to avoid clutter
        Real.magicRounding = true;
      }
      else if (graphCmd == PROG_SOLVE)
      {
        Real.magicRounding = false;
        if (bisect(a,y1,b,y2)) {
          // Let stack always hold best value till now
          stack[0].assign(y1.absLessThan(y2) ? a : b);
        } else {
          // We're done
          stack[0].assign(a);
          progRunning = false;
          Real.magicRounding = true;
          return;
        }
        stackI[0].makeZero(); // We don't want anything complex
        Real.magicRounding = true;
      }
      else if (graphCmd == PROG_INTEGR)
      {
        Real.magicRounding = false;
        if (gh/3+3+integralDepth<gh) {
          // Draw progress
          rTmp3.assign(Real.ONE);
          rTmp3.scalbn(-integralDepth);
          rTmp2.assign(integralN);
          rTmp2.mul(rTmp3);
          rTmp2.add(Real.ONE);
          int x1 = rangeScale(rTmp2,xMin,xMax,gw,Real.ZERO);
          rTmp2.add(rTmp3);
          int x2 = rangeScale(rTmp2,xMin,xMax,gw,Real.ZERO);
          g.setColor(255,255,0);
          g.fillRect(gx+x1,gy+gh/3+3+integralDepth,x2-x1+1,1);
        }

        if (y0.isNan())
          GL4_stripe(y0,y0i,x0,x0i,b,bi,integralDepth,integralN);
        GL4_stripe(y1,y1i,x0,x0i,b,bi,integralDepth+1,(integralN<<1));
        GL4_stripe(y2,y2i,x0,x0i,b,bi,integralDepth+1,(integralN<<1)+1);

        boolean recurse = false;
        if (!y1.isFinite() || !y1i.isFinite() ||
            !y2.isFinite() || !y2i.isFinite())
        {
          // Reached a singularity
          totalExtra  = total. add128(totalExtra, y0, 0);
          totalExtraI = totalI.add128(totalExtraI,y0i,0);
          integralFailed = true;
        } else {
          rTmp.assign(y1);
          rTmp.add(y2);
          rTmp.sub(y0);
          rTmp2.assign(y1i);
          rTmp2.add(y2i);
          rTmp2.sub(y0i);
          if ((rTmp.absLessThan(a) && rTmp2.absLessThan(a)) ||
              (integralN<<2)<0 || integralDepth>=2000)
          {
            totalExtra  = total. add128(totalExtra, y1, 0);
            totalExtraI = totalI.add128(totalExtraI,y1i,0);
            totalExtra  = total. add128(totalExtra, y2, 0);
            totalExtraI = totalI.add128(totalExtraI,y2i,0);
            if ((integralN<<2)<0 || integralDepth>=2000)
              integralFailed = true; // Too much recursion
          } else {
            recurse = true;
          }
        }

        if (recurse) {
          integralDepth++;
          integralN <<= 1;
          y0.assign(y1);
          y0i.assign(y1i);
        } else {
          while ((integralN & 1)!=0) {
            integralDepth--;
            integralN >>= 1;
          }
          integralN++;
          y0.makeNan();
        }

        // Let stack always hold best value till now
        stack[0]. assign(total);
        stackI[0].assign(totalI);
        stack[0]. roundFrom128(totalExtra);
        stackI[0].roundFrom128(totalExtraI);

        Real.magicRounding = true;
        if (integralDepth<63 && (integralN>>integralDepth)!=0) {
          // We're done
          if (stack[0].isFinite() && stackI[0].isFinite() && integralFailed)
            setMessage("Integrate","Failed to produce the desired accuracy");
          progRunning = false;
          return;
        }
      }
      else // Must be min/max
      {
        Real.magicRounding = false;
        if ((maximizing && y0.lessThan(y2)) ||
            (!maximizing && y0.greaterThan(y2))) {
          a.swap(c);
          y0.swap(y2);
        }

        stack[0].assign(a);
        stack[0].add(b);
        stack[0].scalbn(-1);
        executeProgram();
        rTmp.assign(stack[0]);
        if (!rTmp.isFinite() || !stackI[0].isZero()) {
          // Discontinuous or complex function
          stack[0].makeNan();
          stackI[0].makeZero();
          progRunning = false;
          Real.magicRounding = true;
          setMessage("Min/max", "Discontinuous or complex function");
          return;
        }

        if ((maximizing && rTmp.greaterEqual(y1)) ||
            (!maximizing && rTmp.lessEqual(y1))) {
          c.assign(b);
          y2.assign(y1);
          b.add(a);
          b.scalbn(-1);
          y1.assign(rTmp);
        } else {
          a.add(b);
          a.scalbn(-1);
          y0.assign(rTmp);

          stack[0].assign(b);
          stack[0].add(c);
          stack[0].scalbn(-1);
          executeProgram();
          rTmp.assign(stack[0]);
          if (!rTmp.isFinite() || !stackI[0].isZero()) {
            // Discontinuous or complex function
            stack[0].makeNan();
            stackI[0].makeZero();
            progRunning = false;
            Real.magicRounding = true;
            setMessage("Min/max", "Discontinuous or complex function");
            return;
          }

          if ((maximizing && rTmp.greaterEqual(y1)) ||
              (!maximizing && rTmp.lessEqual(y1))) {
            a.assign(b);
            y0.assign(y1);
            b.add(c);
            b.scalbn(-1);
            y1.assign(rTmp);
          } else {
            c.add(b);
            c.scalbn(-1);
            y2.assign(rTmp);
          }
        }

        // Let stack always hold best value till now
        stack[0].assign(b);
        Real.magicRounding = true;

        if (y0.equalTo(y1) && y1.equalTo(y2)) {
          // We're done, it's flat
          progRunning = false;
          return;
        }
      }
    }
    while (System.currentTimeMillis()-start < 500);

    if (graphCmd == PROG_SOLVE || graphCmd == PROG_MINMAX) {
      // Draw progress
      rTmp.assign(a);
      rTmp.sub(b);
      int progress =
        Math.min(Math.max(0,Math.max(a.exponent,b.exponent)-rTmp.exponent),63);
      g.setColor(255,0,128);
      g.fillRect(gx,gy+gh/2-10,progress*gw/63,7);
      g.setColor(255,255,255);
      g.fillRect(gx+progress*(gw-1)/63,gy+gh/2-10,1,7);
    }
    else if (graphCmd == PROG_INTEGR) {
      if (integralN>0) {
        // Draw progress
        x.assign(Real.ONE);
        x.scalbn(-integralDepth);
        rTmp.assign(integralN);
        x.mul(rTmp);
        x.add(Real.ONE);
        xi = rangeScale(x,xMin,xMax,gw-1,Real.ZERO)+1;
        g.setColor(255,0,128);
        g.fillRect(gx,gy+gh/3-10,xi,7);
      }
    }
  }

  // This ends the inner class
  }
  // Create an inner class object to use from the outer
  CalcEngineInner inner = new CalcEngineInner();

  // Now for some convenience methods to access the inner class from the outer
  public boolean prepareGraph(int cmd, int param) {
    return inner.prepareGraph(cmd,param);
  }
  public void startGraph(Graphics g, int gx, int gy, int gw, int gh,
                         boolean bgrDisplay) {
    inner.startGraph(g,gx,gy,gw,gh,bgrDisplay);
  }
  public void continueGraph(Graphics g, int gx, int gy, int gw, int gh) {
    inner.continueGraph(g,gx,gy,gw,gh);
  }
}
