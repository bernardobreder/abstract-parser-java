package org.breder.parser;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.breder.parser.exception.ParserException;
import org.breder.parser.token.WordToken;
import org.breder.parser.token.NumberToken;
import org.breder.parser.token.StringToken;
import org.breder.parser.token.Token;

/**
 * Classe base para o parser lexical
 * 
 * @author Tecgraf
 */
public abstract class AbstractLexer {

  /** Stream */
  private InputStream input;
  /** Próximo caracter */
  protected int look;

  /**
   * Construtor
   * 
   * @param input
   * @throws IOException
   */
  public AbstractLexer(InputStream input) throws IOException {
    this.input = input;
    this.look = Integer.MIN_VALUE;
  }

  /**
   * Realiza a leitura de um Token
   * 
   * @return Token
   * @throws IOException
   * @throws ParserException
   */
  public Token readToken() throws IOException, ParserException {
    if (this.look == -1) {
      return null;
    }
    else if (this.isWord()) {
      return this.readWord();
    }
    else if (this.isNumber()) {
      return this.readNumber();
    }
    else if (this.isString()) {
      return this.readString();
    }
    else {
      return this.readSymbol();
    }
  }

  /**
   * Realiza a leitura de um número
   * 
   * @param c
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public abstract NumberToken readNumber(int c) throws IOException,
    ParserException;

  /**
   * Realiza a leitura de uma String
   * 
   * @param c
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public abstract StringToken readString(int c) throws IOException,
    ParserException;

  /**
   * Realiza a leitura de um simbolo
   * 
   * @param c
   * @return token
   * @throws IOException
   */
  public abstract Token readSymbol(int c) throws IOException;

  /**
   * Realiza a leitura de uma palavra iniciando com uma letra
   * 
   * @param c
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public abstract WordToken readWord(int c) throws IOException,
    ParserException;

  /**
   * Realiza a leitura de um número
   * 
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public StringToken readString() throws IOException, ParserException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return null;
      }
      c = this.next();
    }
    return this.readString(c);
  }

  /**
   * Realiza a leitura de um número
   * 
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public NumberToken readNumber() throws IOException, ParserException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return null;
      }
      c = this.next();
    }
    return this.readNumber(c);
  }

  /**
   * Realiza a leitura de um número
   * 
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public WordToken readWord() throws IOException, ParserException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return null;
      }
      c = this.next();
    }
    return this.readWord(c);
  }

  /**
   * Realiza a leitura de um número
   * 
   * @return token
   * @throws IOException
   * @throws ParserException
   */
  public Token readSymbol() throws IOException, ParserException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return null;
      }
      c = this.next();
    }
    return this.readSymbol(c);
  }

  /**
   * Verifica se o próximo token é um token do tipo número
   * 
   * @return indica se é do tipo número
   * @throws IOException
   */
  public boolean isNumber() throws IOException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return false;
      }
      this.next();
      c = this.look();
    }
    return isNumber(c);
  }

  /**
   * Verifica se o próximo token é um token do tipo string
   * 
   * @return indica se é do tipo string
   * @throws IOException
   */
  public boolean isString() throws IOException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return false;
      }
      this.next();
      c = this.look();
    }
    return isString(c);
  }

  /**
   * Verifica se o próximo token é um token do tipo palavra
   * 
   * @return indica se é do tipo palavra
   * @throws IOException
   */
  public boolean isWord() throws IOException {
    int c = this.look();
    while (c <= 32) {
      if (c < 0) {
        return false;
      }
      this.next();
      c = this.look();
    }
    return isWordStart(c);
  }

  /**
   * Indica se é um número
   * 
   * @param c
   * @return token
   * @throws IOException
   */
  public abstract boolean isNumber(int c) throws IOException;

  /**
   * Indica se é uma string
   * 
   * @param c
   * @return token
   * @throws IOException
   */
  public abstract boolean isString(int c) throws IOException;

  /**
   * Indica se é uma palavra
   * 
   * @param c
   * @return token
   * @throws IOException
   */
  public abstract boolean isWordStart(int c) throws IOException;

  /**
   * Indica se é uma palavra
   * 
   * @param c
   * @return token
   * @throws IOException
   */
  public abstract boolean isWordPart(int c) throws IOException;

  /**
   * Realiza a leitura de somente um byte
   * 
   * @return token
   * @throws IOException
   */
  protected int look() throws IOException {
    if (this.look == Integer.MIN_VALUE) {
      this.look = this.read();
    }
    return this.look;
  }

  /**
   * Anda para o próximo byte
   * 
   * @return token
   * 
   * @throws IOException
   */
  public int next() throws IOException {
    return this.look = this.read();
  }

  /**
   * Realiza a leitura
   * 
   * @return leitura
   * @throws IOException
   */
  private int read() throws IOException {
    int c = this.input.read();
    if (c <= 0x7F) {
      return c;
    }
    else if ((c >> 5) == 0x6) {
      int i2 = this.input.read();
      if (i2 < 0) {
        throw new EOFException();
      }
      return (((c & 0x1F) << 6) + (i2 & 0x3F));
    }
    else {
      int i2 = this.input.read();
      if (i2 < 0) {
        throw new EOFException();
      }
      int i3 = this.input.read();
      if (i3 < 0) {
        throw new EOFException();
      }
      return (((c & 0xF) << 12) + ((i2 & 0x3F) << 6) + (i3 & 0x3F));
    }
  }

}
