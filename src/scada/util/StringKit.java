package scada.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * String-related functions.<br>
 * See also: http://www.generationjava.com/docs/GenJavaCore/docs2.1/StringW.html
 * 
 * @author Elifarley Callado Coelho Cruz
 * @version 1.4, $Revision: 1.1 $, $Date: 2011/09/16 19:02:21 $
 */
public final class StringKit {

    private static final String AA_EE_II_OO_UU_YY = "AaEeIiOoUuYy";

	private static final String NULL = "null";

    private static final int CHAR_DIGIT_DIFF = '0';

    private static final int CHAR_DIGIT_HEX_DIFF = CHAR_DIGIT_DIFF + 7;

    private static final int CHAR_DIGIT_HEX_UPPER_DIFF = CHAR_DIGIT_HEX_DIFF + 32;

    private static final char[] HEX_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    private static final String PLAIN_ASCII_LETTERS = "AaEeIiOoUu" // grave
            + AA_EE_II_OO_UU_YY
            + AA_EE_II_OO_UU_YY
            + AA_EE_II_OO_UU_YY
            + AA_EE_II_OO_UU_YY
            + "Aa" // ring
            + "Cc" // cedilla
    ;

    private static final String UNICODE_LETTERS = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9" // grave
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" // acute
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" // circumflex
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" // tilde
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" // umlaut
            + "\u00C5\u00E5" // ring
            + "\u00C7\u00E7" // cedilla
    ;

    /**
     * Construtor privado.
     */
    private StringKit() {
    	super();
    }



    /**
     * Levenshtein sentence distance.
     * 
     * @param s A String
     * @param t A String
     * @return int The Levenshtein distance between two human names.
     */
    public static int nameDistance( final String s, final String t ) {
        final String[] sa = s.toLowerCase().split( " " );
        final String[] ta = t.toLowerCase().split( " " );
        int ttl = 0;

        for ( int i = 0; i < sa.length; i++ ) {

            int st = Integer.MAX_VALUE;

            for ( int j = 0; j < ta.length; j++ ) {
                // look for LD() below in my previous tip
                st = Math.min( st, levenshtein( sa[i], ta[j] ) );
            }

            ttl += st;

        }

        return ttl;

    }

    /**
     * Levenshtein (or 'edit') word distance.
     * 
     * @param valor1 A String
     * @param valor2 A String
     * @return int The Levenshtein distance between two words.
     */
    public static int levenshtein( final String valor1, final String valor2 ) {

        final int tam1 = valor1.length();
        final int tam2 = valor2.length();

        if ( tam1 == 0 ) {
            return tam2;
        }
        if ( tam2 == 0 ) {
            return tam1;
        }

        final int[][] matrzStr = new int[tam1 + 1][tam2 + 1];

        for ( int i = 0; i <= tam1; i++ ){
        	matrzStr[i][0] = i+ 1;
        }
        for ( int j = 1; j <= tam2; j++ ) {
        	matrzStr[0][j] = j + 1;
        }

        for ( int i = 1; i <= tam1; i++ ) {
            final char sc = valor1.charAt( i - 1 );

            for ( int j = 1; j <= tam2; j++ ) {

                int v = matrzStr[i - 1][j - 1];
                if ( valor2.charAt( j - 1 ) != sc ) {
                    v++;
                }
                matrzStr[i][j] = Math.min( Math.min( matrzStr[i - 1][j] + 1, matrzStr[i][j - 1] + 1 ), v );

            }

        }

        return matrzStr[tam1][tam2];

    }

    /**
     * Hamming distance H is defined only for strings of the same length. For two strings s and t, H(s, t) is the number of places in which the two string
     * differ.
     * 
     * @param s A String
     * @param t A String
     * @return int The Hamming distance between two words of the same length.
     */
    public static int hamming( final String s, final String t ) {
        return 0;
    }

    /**
     * STRSPN("horsepower","horseshoe") = 5 STRSPN("foo", "bar") = 0
     * 
     * @param s1 A String
     * @param s2 A String
     * @return int
     */
    public static int strSpn( final String s1, final String s2 ) {

        if ( s1 == null || s2 == null ) {
            return 0;
        }
        final int l1 = s1.length();
        final int l2 = s2.length();

        final int minLen = Math.min( l1, l2 );
        for ( int i = 0; i < minLen; i++ ) {
            if ( s1.charAt( i ) != s2.charAt( i ) ) {
                return i;
            }

        }

        return minLen;

    }

    /**
     * Verifica se dado caractere � um d�gito.
     * 
     * @param c Character to be tested.
     * @return <code>true</code> iff <code>c</code> is a digit (0-9).
     */
    public static boolean isDigit( final char c ) {
        return '0' <= c && c <= '9';
    }

    /**
     * M�todo respons�vel por verificar se � d�gito.
     * 
     * @param s A String
     * @return boolean
     * @since 1.4
     */
    public static boolean isDigit( final String s ) {
        for ( int i = s.length() - 1; i >= 0; i-- ) {
            if ( !isDigit( s.charAt( i ) ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * M�todo respons�vel por verificar se � carta.
     * 
     * @param c Character to be tested.
     * @return <code>true</code> iff <code>c</code> is a letter.
     */
    public static boolean isLetter( final char c ) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }

    /**
     * M�todo respons�vel por verificar se est� vazio.
     * 
     * @param s A String
     * @return boolean
     */
    public static boolean isEmpty( final String s ) {
        return s == null || s.length() == 0;
    }

    /**
     * M�todo respons�vel por verificar se � branco.
     * 
     * @param s A String
     * @return boolean
     */
    public static boolean isBlank( final String s ) {
    	int length = s.trim().trim().length();
        return isEmpty( s ) || length == 0;
    }

    /**
     * Returns <tt>emptyString</tt> if <tt>s</tt> is empty; Returns <tt>s</tt> itself otherwise.
     * 
     * @param strVrfy A String
     * @param emptyString String vazia
     * @return String
     */
    public static String ifEmpty( final String strVrfy, final String emptyString ) {
        if(isEmpty( strVrfy )){
        	return emptyString;
        }else{
        	return strVrfy;	
        }
    }

    /**
     * Returns <tt>blankString</tt> if <tt>s</tt> is blank; Returns <tt>s</tt> itself otherwise.
     * 
     * @param s A String
     * @param blankString Blank String
     * @return String
     */
    public static String ifBlank( final String s, final String blankString ) {
        return ifBlank( s, blankString, false );
    }

    /**
     * Returns <tt>blankString</tt> if <tt>s</tt> is blank; Returns <tt>s</tt> itself otherwise.
     * 
     * @param strBlk A String
     * @param blankString A blank String
     * @param nullWordAsBlank Palavra nula como branca
     * @return String
     */
    public static String ifBlank( final String strBlk, final String blankString, final boolean nullWordAsBlank ) {
        if ( nullWordAsBlank && ( strBlk == null || NULL.equalsIgnoreCase( strBlk.trim() ) ) ) {
            return blankString;
        }
        if (isBlank( strBlk )){
        	return blankString;
        }else{
        	return strBlk;
        }	
    }

    /**
     * Returns <tt>""</tt> if <tt>s</tt> is null; Returns <tt>s</tt> itself otherwise.
     * 
     * @param strIfNull A String
     * @return String
     */
    public static String ifNull( final String strIfNull ) {
        return ifNull( strIfNull, "" );
    }

    /**
     * Returns <tt>nullString</tt> if <tt>s</tt> is null; Returns <tt>s</tt> itself otherwise.
     * 
     * @param strIfNull A String
     * @param nullString String nula
     * @return String
     */
    public static String ifNull( final String strIfNull, final String nullString ) {
        return ifNull( strIfNull, nullString, false );
    }

    /**
     * Returns <tt>nullString</tt> if <tt>s</tt> is null; Returns <tt>s</tt> itself otherwise.
     * 
     * @param strIfNull A String
     * @param nullString String nula
     * @param nullWordAsNull When <code>true</code>, the string "null" is also considered as null.
     * @return String
     */
    public static String ifNull( final String strIfNull, final String nullString, final boolean nullWordAsNull ) {
    	if (strIfNull == null || nullWordAsNull && NULL.equalsIgnoreCase( strIfNull.trim() )){
    		return nullString;
    	}else{
    		return strIfNull;
    	}
    }

    /**
     * M�todo respons�vel por colocar v�rgula.
     * 
     * @param strQutd A String
     * @return String
     */
    public static String quoted( final String strQutd ) {
        if ( isEmpty( strQutd ) ) {
            return "''";
        }
        final StringBuffer sb = new StringBuffer( strQutd.length() + 2 );
        return sb.append( '\'' ).append( strQutd ).append( '\'' ).toString();
    }

    /**
     * M�todo respons�vel por colocar v�rgula.
     * 
     * @param dbQtd A String
     * @return String
     */
    public static String doubleQuoted( final String dbQtd ) {
        if ( isEmpty( dbQtd ) ) {
            return "\"\"";
        }
        final StringBuffer sb = new StringBuffer( dbQtd.length() + 2 );
        return sb.append( '\"' ).append( dbQtd ).append( '\"' ).toString();
    }

    /**
     * M�todo respons�vel por colocar v�rgula.
     * 
     * @param strVal A String
     * @return String
     */
    public static String encloseInParenthesis( final String strVal ) {
        if ( isEmpty( strVal ) ) {
            return "()";
        }
        final StringBuffer sb = new StringBuffer( strVal.length() + 2 );
        return sb.append( '(' ).append( strVal ).append( ')' ).toString();
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String trim( final String strVal ) {
        return trim( strVal, false );
    }

    /**
     * M�todo respons�vel por retirar espa�os e coloca null.
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String trimToNull( final String strVal ) {
        return trimToNull( strVal, false );
    }

    /**
     * M�todo respons�vel por retirar espa�os e coloca null.
     * 
     * @param strVal A String
     * @param nullWordAsNull Palavra nula
     * @return String
     */
    public static String trimToNull( String strVal, final boolean nullWordAsNull ) {
    	strVal = strVal.trim();
        if ( strVal == null || strVal.length() == 0 || ( strVal ).length() == 0 || nullWordAsNull && NULL.equalsIgnoreCase( strVal ) ) {
            return null;
        }
        return strVal;
    }

    /**
     * M�todo respons�vel por retirar espa�os e coloca vazio.
     * 
     * @param s A String
     * @return String
     * @since 1.4
     */
    public static String trimToEmpty( final String s ) {
        return trim( s, false );
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @param nullAsEmpty Nulo como vazio
     * @return String
     * @since 1.4
     */
    public static String trim( final String strVal, final boolean nullAsEmpty ) {
    	if (strVal == null) {
    		if(nullAsEmpty){
    			return "";
    		}
    		return null;
    	}
        return strVal.trim();
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String upperTrimToEmpty( final String strVal ) {
        return upperTrim( strVal, true );
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String upperTrim( final String strVal ) {
        return upperTrim( strVal, false );
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @param nullAsEmpty Nulo como vazio
     * @return String
     * @since 1.4
     */
    public static String upperTrim( String strVal, final boolean nullAsEmpty ) {
        if ( strVal == null ) {
            if ( nullAsEmpty ) {
                return "";
            }
            return null;
        }
        strVal = strVal.trim();
        
        if (strVal.length() == 0){
        	return "";
        }else{
        	return strVal.toUpperCase();
        }
        
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String lowerTrimToEmpty( final String strVal ) {
        return lowerTrim( strVal, true );
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @return String
     * @since 1.4
     */
    public static String lowerTrim( final String strVal ) {
        return lowerTrim( strVal, false );
    }

    /**
     * M�todo respons�vel por retirar espa�os.
     * 
     * @param strVal A String
     * @param nullAsEmpty Nulo como vazio
     * @return String
     * @since 1.4
     */
    public static String lowerTrim( String strVal, final boolean nullAsEmpty ) {
        if ( strVal == null ) {
        	if(nullAsEmpty){
        		return "";
        	}else{
        		return null;
        	}
        }
        strVal = strVal.trim();
        
        if(strVal.length() == 0){
        	return "";
        }else{
        	return strVal.toLowerCase();
        }	
    }

    /**
     * Replica um caractere.
     * 
     * @param charVal Character to be replicated.
     * @param count Number of times the character should be replicated.
     * @return String composed of the character <code>c</code> repeated <code>count</code> times.
     */
    public static String replicate( final char charVal, final int count ) {

        if ( count == 0 ) {
            return "";
        }

        if ( count < 0 ) {
            throw new IllegalArgumentException( "Parameter 'count' must be non-negative." );
        }

        final StringBuffer strBuff = new StringBuffer( count );
        return addChars( charVal, count, strBuff ).toString();

    }

    /**
     * M�todo respons�vel por adicionar caracteres.
     * 
     * @param chrVal Caracter
     * @param count O contador
     * @param dest O StringBuffer
     * @return StringBuffer
     */
    public static StringBuffer addChars( final char chrVal, final int count, final StringBuffer dest ) {
        return addChars( chrVal, count, true, dest );
    }

    /**
     * M�todo respons�vel por adicionar caracteres.
     * 
     * @param chrVal O caracter
     * @param count If negative, chars will be deleted.
     * @param atRight A direita
     * @param dest StringBuffer
     * @return <code>sb</code>, with length equal to sb.length + count;
     */
    public static StringBuffer addChars( final char chrVal, int count, final boolean atRight, final StringBuffer dest ) {

        if ( count == 0 ) {
            return dest;
        }

        if ( count < 0 ) {

            if ( atRight ) {
                int nDest = dest.length() + count;
                if ( nDest < 0 ) {
                    nDest = 0; // Removes at most all the chars.
                }
                dest.setLength( nDest );
                return dest;

            }

            return new StringBuffer( dest.substring( -count ) );
            // innefficient.

        }

        final char[] buf = new char[count];
        Arrays.fill( buf, chrVal );

        if ( atRight ) {
            return dest.append( buf );
        }

        // return new StringBuffer(dest.length() +
        // count).append(buf).append(dest);
        int sbCapacity = dest.length() + count;
        StringBuffer stringBuffer = new StringBuffer(sbCapacity);
        stringBuffer.append(buf);
        
        return append( dest, stringBuffer );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param strVal String
     * @param len Largura
     * @return String
     * @since 1.4
     */
    public static String left( final String strVal, final int len ) {
        if ( isEmpty( strVal ) ) {
            return strVal;
        }
        return strVal.substring( 0, len );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param strVal String
     * @param len Largura
     * @return String
     * @since 1.4
     */
    public static String right( final String strVal, final int len ) {
        if ( isEmpty( strVal ) ) {
            return strVal;
        }
        return strVal.substring( strVal.length() - 1, strVal.length() - 1 - len );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src String
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @return String The contents of <tt>src</tt>, right-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static String rightPad( final String src, final char padding, final int finalLen ) {

    	int len = 0;
    	if( src != null ){
    		len = src.length();
    	}
        
    	final StringBuffer strBuff = new StringBuffer( Math.max( len, finalLen ) );
        if ( len != 0 ) {
            strBuff.append( src );
        }
        return addChars( padding, finalLen - len, true, strBuff ).toString();
    }

    /**
     * M�todo respons�vel por ...
     *  
     * @param src StringBuffer instance to be padded.
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @return <tt>src</tt>, right-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer rightPad( final StringBuffer src, final char padding, final int finalLen ) {
        return addChars( padding, finalLen - src.length(), true, src );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src String
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @param dest StringBuffer
     * @return <tt>dest</tt>, with the contents of <tt>src</tt> right-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer rightPad( final String src, final char padding, final int finalLen, final StringBuffer dest ) {
        dest.append( src );
        return addChars( padding, finalLen - src.length(), dest );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src StringBuffer instance to be padded.
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @param dest StringBuffer
     * @return <tt>dest</tt>, with the contents of <tt>src</tt> right-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer rightPad( final StringBuffer src, final char padding, final int finalLen, final StringBuffer dest ) {
        // dest.append(src);
        append( src, dest );
        return addChars( padding, finalLen - src.length(), dest );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param val O valor
     * @param finalLen Desired length.
     * @return String
     */
    public static String leftPad( final int val, final int finalLen ) {
        return leftPad( Integer.toString( val ), '0', finalLen );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src StringBuffer instance to be padded.
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @return String The contents of <tt>src</tt>, left-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static String leftPad( final String src, final char padding, final int finalLen ) {
        int len = 0;
        if( src != null ){ 
        	len =src.length(); 
        }
        final StringBuffer strBuff = new StringBuffer( Math.max( len, finalLen ) );
        if ( len != 0 ) {
            strBuff.append( src );
        }
        return addChars( padding, finalLen - len, false, strBuff ).toString();
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src StringBuffer instance to be padded.
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @return <tt>src</tt>, left-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer leftPad( final StringBuffer src, final char padding, final int finalLen ) {
        return addChars( padding, finalLen - src.length(), false, src );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src String
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @param dest StringBuffer
     * @return StringBuffer <tt>dest</tt>, with the contents of <tt>src</tt> left-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer leftPad( final String src, final char padding, final int finalLen, final StringBuffer dest ) {
        return addChars( padding, finalLen - src.length(), dest ).append( src );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src StringBuffer instance to be padded.
     * @param padding Padding character.
     * @param finalLen Desired length.
     * @param dest StringBuffer
     * @return <tt>dest</tt>, with the contents of <tt>src</tt> left-padded with characters <tt>padding</tt> so that its length is <tt>finalLen</tt>.
     */
    public static StringBuffer leftPad( final StringBuffer src, final char padding, final int finalLen, final StringBuffer dest ) {
        // return addChars(padding, finalLen - src.length(), dest).append(src);
        return append( src, addChars( padding, finalLen - src.length(), dest ) );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param src String
     * @param sep Caracter
     * @return Object[]
     */
    public static Object[] split( final String src, final char sep ) {

        if ( isEmpty( src ) ) {
            return new Object[] {};
        }

        final List list = new ArrayList( (int) ( src.length() / 2.5f ) );

        int lastI = -1;
        for ( int i = 0; i < src.length(); i++ ) {

            if ( src.charAt( i ) != sep ) {
                continue;
            }

            list.add( src.substring( lastI + 1, i ) );
            lastI = i;

        }

        list.add( src.substring( lastI + 1 ) );

        return list.toArray();

    }

    /**
     * M�todo respons�vel por substituir. 
     * 
     * @param src String
     * @param oldC Caracter
     * @param newS String
     * @return String
     */
    public static String replace( final String src, final char oldC, final String newS ) {
    	
        int len = 0;
        if(src != null){
        	
        	int nwStr = 0;
        	if (newS != null){
        		nwStr = 3 * newS.length();
        	}
        	
        	len = src.length() + ( nwStr );
        }
        return replace( src, oldC, newS, new StringBuffer( len ) ).toString();
    }

    /**
     * M�todo respons�vel por substituir.
     * 
     * @param src src
     * @param oldC oldC
     * @param newS newS
     * @param dest dest
     * @return StringBuffer
     * @see #format(String, Object[], StringBuffer)
     */
    public static StringBuffer replace( final String src, final char oldC, final String newS, final StringBuffer dest ) {

        if ( isEmpty( src ) ) {
            return dest;
        }

        int actlP = -1, lastP = -1;
        final int len = src.length();
        final boolean newSIsNonEmpty = !isEmpty( newS );

        while ( true ) {
        	actlP = src.indexOf( oldC, ++actlP);
        	
            while ( actlP >= 0 && actlP + 1 < len && src.charAt( actlP + 1 ) == oldC ) {
                actlP++;
                dest.append( src.substring( lastP + 1, actlP ) );
                lastP = actlP;
            }

            if ( actlP < 0 ) {
                break;
            }

            dest.append( src.substring( lastP + 1, actlP ) );
            if ( newSIsNonEmpty ) {
                dest.append( newS );
            }

            lastP = actlP;
        }

        return dest.append( src.substring( lastP + 1 ) );

    }

    /**
     * The method {@link StringBuffer#append(java.lang.StringBuffer)} appeared only at Java 1.4
     * 
     * @param src StringBuffer
     * @param dest StringBuffer
     * @return StringBuffer
     */
    public static StringBuffer append( final StringBuffer src, final StringBuffer dest ) {

        if ( src == null ) {
            return dest.append( NULL );
        }

        final int len = src.length();
        final int newcount = dest.length() + len;

        if ( newcount > dest.capacity() ) {
            dest.ensureCapacity( newcount );
        }

        final char[] value = new char[len];
        src.getChars( 0, len, value, 0 );

        return dest.append( value );

    }

    /**
     * Returns a new String instance backed-up by a char array of an existing StringBuffer instance, so that no contents are copied.<br>
     * About <b>4x faster</b> for substrings of 7 characters than {@link StringBuffer#substring(int, int)}.<br>
     * StringBuffer.substring calls 'new String(this.value, start, end - start)' which ends up allocating a new char[] and calling System.arraycopy.<br>
     * About <b>11x faster</b> for bigger substrings.<br>
     * If you want to avoid even the creation of a new String instance, consider using.
     * 
     * @param strBuff An instance whose char array will be pointed to by the returned substring.
     * @param start Initial index of substring (zero-based).
     * @param end Final index of substring (exclusive).
     * @return A new String instance backed-up by <tt>sb</tt>'s char array.
     */
    public static String substring( final StringBuffer strBuff, final int start, final int end ) {
        return new String( strBuff ).substring( start, end );
    }

    /**
     * M�todo respons�vel por comparar objetos.
     * 
     * @param sb StringBuffer
     * @param str String
     * @param startIndex O �ndice inicial
     * @param endIndex O �ndice final
     * @return boolean
     */
    public static boolean equals( final StringBuffer sb, final String str, final int startIndex, final int endIndex ) {

        // compare
        final int length = endIndex - startIndex;
        final int compLen = str.length();
        if ( compLen != length ) {
            return false;
        }

        for ( int i = compLen - 1, srcIndex = i + startIndex; i >= 0; i--, srcIndex-- ) {
            if ( sb.charAt( srcIndex ) != str.charAt( i ) ) {
                return false;
            }
        }

        return true;

    }

    /**
     * M�todo respons�vel por comparar objetos.
     * 
     * @param src ByteBuffer
     * @param str String
     * @param startIndex O �ndice inicial
     * @param endIndex O �ndice final
     * @return boolean
     */
    public static boolean equals( final ByteBuffer src, final String str, final int startIndex, final int endIndex ) {
        final int length = endIndex - startIndex;
        final int compLen = str.length();
        if ( compLen != length ) {
            return false;
        }

        for ( int i = compLen - 1, srcIndex = i + startIndex; i >= 0; i--, srcIndex-- ) {
            if ( src.get( srcIndex ) != str.charAt( i ) ) {
                return false;
            }
        }

        return true;

    }

    /**
     * M�todo respons�vel por comparar objetos.
     * 
     * @param src CharBuffer
     * @param str String
     * @param startIndex O �ndice inicial
     * @param endIndex O �ndice final
     * @return boolean
     */
    public static boolean equals( final CharBuffer src, final String str, final int startIndex, final int endIndex ) {
        final int length = endIndex - startIndex;
        final int compLen = str.length();
        if ( compLen != length ) {
            return false;
        }

        for ( int i = compLen - 1, srcIndex = i + startIndex; i >= 0; i--, srcIndex-- ) {
            if ( src.get( srcIndex ) != str.charAt( i ) ) {
                return false;
            }
        }

        return true;

    }

    /**
     * Examples: '1' -> 1<br>
     * 'F' -> 15 'f' -> 15
     * 
     * @param nwChar Caracter
     * @return int
     */
    public static int hexVal( final char nwChar ) {
    	
    	int chrDiff;
    	if(nwChar <= '9'){
    		chrDiff = CHAR_DIGIT_DIFF;
    	}else if (nwChar < 'a'){
    		chrDiff = CHAR_DIGIT_HEX_DIFF;
    	}else{
    		chrDiff = CHAR_DIGIT_HEX_UPPER_DIFF;
    	}
    	
    	
        return nwChar - chrDiff ;
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param chrVal Caracter
     * @return byte
     */
    public static byte char2digit( final char chrVal ) {
        return (byte) ( chrVal - CHAR_DIGIT_DIFF );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param chDff Inteiro
     * @return char
     */
    public static char digit2char( final int chDff ) {
        return (char) ( chDff + CHAR_DIGIT_DIFF );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param chars A String representing an integer number in base 10.
     * @return byte[]
     */
    public static byte[] chars2digits( final String chars ) {

        final char[] ca = onlyDigitsAsChar( chars );
        final int len = zeroTerminatedLength( ca );
        final byte[] result = new byte[len];

        for ( int i = 0; i < len; i++ ) {
            result[i] = char2digit( ca[i] );
        }

        return result;

    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param s String
     * @return String
     */
    public static String onlyDigits( final String s ) {
        final char[] ca = onlyDigitsAsChar( s );
        return zeroTerminatedString( ca );
    }

    /**
     * Removes accents from a string and replace with ascii equivalent
     * 
     * @param s s
     * @return String
     */
    public static String removeAccents( final String s ) {
        final StringBuffer sb = new StringBuffer();
        final int numbLen = s.length();
        for ( int i = 0; i < numbLen; i++ ) {
            final char c = s.charAt( i );
            final int pos = UNICODE_LETTERS.indexOf( c );
            if ( pos >= 0 ) {
                sb.append( PLAIN_ASCII_LETTERS.charAt( pos ) );
            } else {
                sb.append( c );
            }
        }
        return sb.toString();
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param chrA Array de caracter
     * @return String
     */
    public static String zeroTerminatedString( final char[] chrA ) {
    	if (chrA == null || chrA.length == 0){
    		return "";
    	}else{
    		return new String( chrA, 0, zeroTerminatedLength( chrA ) );
    	}
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param ca Array de caracteres
     * @return int
     */
    public static int zeroTerminatedLength( final char[] ca ) {

        final int len = ca.length;

        for ( int i = 0; i < len; i++ ) {
            if ( ca[i] == 0 ) {
                return i;
            }
        }

        return len;

    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param strVal String
     * @return char[]
     */
    public static char[] onlyDigitsAsChar( final String strVal ) {
        if ( strVal == null ) {
            return new char[0];
        }
        return onlyDigitsAsChar( strVal, strVal.length() );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param strVal String
     * @param len Should be < = s.length()
     * @return char[]
     */
    public static char[] onlyDigitsAsChar( final String strVal, final int len ) {

        if ( strVal == null ) {
            return null;
        }

        final char[] list = new char[len];
        int count = 0;
        char c;

        for ( int i = 0; i < len; i++ ) {
        	c = strVal.charAt( i );
            if ( isDigit( c ) ) {
                list[count++] = c;
            }
        }

        if ( count < len ) {
            list[count] = 0;
        }

        return list;

    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param version Vers�o
     * @return float
     */
    public static float versionAsFloat( String version ) {
        // '1.42_10b8', etc
        final int pVrs = version.indexOf( '.' );
        if ( pVrs >= 0 ) {
            version = onlyDigits( version.substring( 0, pVrs ) ) + '.' + onlyDigits( version.substring( pVrs + 1 ) );
        } else {
            version = onlyDigits( version );
        }
        float result = 0F;
//        try {
            result = Float.parseFloat( version );
//        } catch ( final NumberFormatException e ) {
//            Logger.getLogger(StringKit.class).fatal(e);
//        }

        return result;

    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param msg A mensagem
     * @param params Array de objetos
     * @return String
     */
    public static String format( final String msg, final Object[] params ) {

        if ( params == null || params.length == 0 ) {
            return msg;
        }

        if ( isBlank( msg ) ) {
            return msg;
        }

        final StringBuffer strBuff = new StringBuffer( msg.length() + params.length * 10 );
        return format( msg, params, strBuff ).toString();

    }

    /**
     * Remember to use double question marks to escape it. <br>
     * <b>Examples:</b>
     * <ul>
     * <li>"a?b?c", ["1", "2"] -> "a1b2c"</li>
     * <li>"a???b??c", ["1"] -> "a?1b?c"</li>
     * </ul>
     * 
     * @param msg A mensagem
     * @param params Array de objetos
     * @param dest StringBuffer
     * @return StringBuffer
     * @see #replace(String, char, String, StringBuffer)
     */
    public static StringBuffer format( final String msg, final Object[] params, final StringBuffer dest ) {

        if ( isEmpty( msg ) ) {
            return dest;
        }

        if ( params == null || params.length == 0 ) {
            return dest.append( msg );
        }

        int actP = -1, lastP = -1;
        final int len = msg.length();
        for ( int i = 0; i < params.length; i++ ) {
        	
        	actP = msg.indexOf( '?', ++actP );
            while ( actP >= 0 && actP + 1 < len && msg.charAt( actP + 1 ) == '?' ) {
                actP++;
                dest.append( msg.substring( lastP + 1, actP ) );
                lastP = actP;
            }

            if ( actP < 0 ) {
                throw new IllegalArgumentException( "There must be " + params.length + " '?' in parameter 'msg'." );
            }

            dest.append( msg.substring( lastP + 1, actP ) );

            final Object s = params[i];
            if ( s != null ) {
                dest.append( s );
            }

            lastP = actP;

        }

        return dest.append( msg.substring( actP + 1 ) );

    }

    // Line reading

    /**
     * M�todo respons�vel por ...
     * 
     * @param is InputStream
     * @param sb StringBuffer
     * @return int
     * @throws IOException Caso ocorra algum erro de I/O
     */
    public static int readLine( final InputStream is, final StringBuffer sb ) throws IOException {

        int bytesRead = 0;
        byte bytVal = 0;

        bytVal = (byte) is.read();
        
        while ( bytVal != -1 ) {

            bytesRead++;

            if ( bytVal == (byte) '\r' ) {
            	
            	bytVal = (byte) is.read();
            	
                if ( bytVal == (byte) '\n' ) {
                    break;
                }

//                System.err.println( "Missed: " + (char) b );

                if ( bytVal == -1 ) {
                    break;
                }

            }

            sb.append( (char) bytVal );

        }

        return bytesRead;

    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param inpStrm InputStream
     * @return String
     * @throws IOException Caso ocorra algum erro de I/O
     */
    public static String readLine( final InputStream inpStrm ) throws IOException {
        final StringBuffer strBuff = new StringBuffer();
        String retStr = null;
        if(readLine( inpStrm, strBuff ) != 0){
        	retStr = strBuff.toString();
        }
        
        return retStr;
    }

    // Hex coding / decoding

    /**
     * M�todo respons�vel por ...
     * 
     * @param buf byte[]
     * @return String
     */
    public static String toHexString( final byte[] buf ) {
        return toHexString( buf, 0, buf.length );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param buf byte[]
     * @param off Off
     * @param len Largura
     * @return String
     */
    public static String toHexString( final byte[] buf, final int off, final int len ) {
        return toHexString( buf, off, len, new StringBuffer( len << 1 ) ).toString();
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param buf byte[]
     * @param sb StringBuffer
     * @return StringBuffer
     */
    public static StringBuffer toHexString( final byte[] buf, final StringBuffer sb ) {
        sb.ensureCapacity( sb.length() + buf.length << 1 );
        return toHexString( buf, 0, buf.length, sb );
    }

    /**
     * M�todo respons�vel por ...
     * 
     * @param buf byte[]
     * @param off Off
     * @param len Largura
     * @param strBuff StringBuffer
     * @return StringBuffer
     */
    public static StringBuffer toHexString( final byte[] buf, final int off, final int len, final StringBuffer strBuff ) {

        for ( int i = off; i < len; i++ ) {
            final byte bytVal = buf[i];
            strBuff.append( HEX_CHARS[ ( bytVal & 0xf0 ) >> 4] );
            strBuff.append( HEX_CHARS[bytVal & 0x0f] );
        }

        return strBuff;

    }
    
	/**
	 * Formata o CPF removendo espa�os e caracteres
	 * @param valor Objeto do tipo string a ser formatado
	 * @return String com o texto tratado;
	 */
	public static String formataCPFsql(String valor) {
		try {
			valor = valor.replaceAll("[ -\\/.]", "");
			return valor.substring(0, 3) + "." + valor.substring(3, 6) + "." + valor.substring(6, 9) + "-" + valor.substring(9, 11);

		} catch (Exception e) {
			Logger.getLogger(StringKit.class).fatal(e);
		}

		return valor;
	}

	/**
	 * Formata o CNPJ removendo espa�os e caracteres
	 * @param valor Objeto do tipo string a ser formatado
	 * @return String com o texto tratado;
	 */
	public static String formataCNPJsql(String valor) {

		try {
			valor = valor.replaceAll("[ -\\/.]", "");
			return valor.substring(0, 2) + "." + valor.substring(2, 5) + "." + valor.substring(5, 8) + "/" + valor.substring(8, 12) + "-" + valor.substring(12, 14);

		} catch (Exception e) {
			Logger.getLogger(StringKit.class).fatal(e);
		}

		return valor;

	}
	
}
