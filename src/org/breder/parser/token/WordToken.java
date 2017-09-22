package org.breder.parser.token;

import java.util.HashMap;
import java.util.Map;

/**
 * Token de palavras reservadas
 * 
 * @author Tecgraf
 */
public class WordToken extends Token {

  /** Imagem */
  public String lexeme = "";
  /** Palavras */
  private static final Map<String, WordToken> words =
    new HashMap<String, WordToken>();
  /** Token Ids */
  public final static int ID = -1;
  /** Token Ids */
  public final static int NUM = -2;
  /** Token Ids */
  public final static int STR = -3;

  /**
   * Construtor
   * 
   * @param s
   * @param tag
   */
  public WordToken(String s, int tag) {
    super(tag);
    lexeme = s;
  }

  /**
   * Constroi um token
   * 
   * @param token
   * @return token
   */
  public static WordToken build(String token) {
    return words.get(token);
  }

  /**
   * Adiciona um novo token
   * 
   * @param token
   * @param word
   */
  public static void put(String token, WordToken word) {
    words.put(token, word);
  }

  /**
   * Constroi um token
   * 
   * @param tag
   * @return token
   */
  public static WordToken build(int tag) {
    for (WordToken token : words.values()) {
      if (token.tag == tag) {
        return token;
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((lexeme == null) ? 0 : lexeme.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    WordToken other = (WordToken) obj;
    if (lexeme == null) {
      if (other.lexeme != null) {
        return false;
      }
    }
    else if (!lexeme.equals(other.lexeme)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return lexeme;
  }

}
