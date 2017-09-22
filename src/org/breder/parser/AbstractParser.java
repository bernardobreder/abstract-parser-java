package org.breder.parser;

import java.io.IOException;

import org.breder.parser.exception.ParserException;
import org.breder.parser.exception.SyntaxException;
import org.breder.parser.token.Token;

/**
 * Classe base para criar uma parser
 * 
 * @author bernardobreder
 * 
 */
public abstract class AbstractParser {

  /** Lexical */
  protected final AbstractLexer lexer;
  /** Próximo token */
  protected Token look;

  /**
   * Construtor
   * 
   * @param lexer
   */
  public AbstractParser(AbstractLexer lexer) {
    super();
    this.lexer = lexer;
  }

  /**
   * Indica se foi encontrado o token de uma tag especifica
   * 
   * @param type
   * @return token encontrado da tag
   * @throws ParserException
   * @throws IOException
   */
  protected boolean match(int type) throws IOException, ParserException {
    Token look = this.look();
    if (look == null) {
      return false;
    }
    return look.tag == type;
  }

  /**
   * Indica se foi encontrado o token de uma tag especifica
   * 
   * @param type
   * @return token encontrado da tag
   * @throws ParserException
   * @throws IOException
   */
  protected Token read(int type) throws IOException, ParserException {
    if (this.lookTag() != type) {
      throw new SyntaxException();
    }
    Token result = this.look;
    this.next();
    return result;
  }

  /**
   * Retorna o token corrente
   * 
   * @return token encontrado da tag
   * @throws ParserException
   * @throws IOException
   */
  protected Token look() throws IOException, ParserException {
    if (this.look == null) {
      this.look = this.lexer.readToken();
    }
    return this.look;
  }

  /**
   * Retorna o token corrente
   * 
   * @return token encontrado da tag
   * @throws ParserException
   * @throws IOException
   */
  protected int lookTag() throws IOException, ParserException {
    Token look = this.look();
    if (look == null) {
      return -1;
    }
    else {
      return look.tag;
    }
  }

  /**
   * Anda para o próximo token
   * 
   * @return próximo token
   * @throws IOException
   * @throws ParserException
   */
  protected Token next() throws IOException, ParserException {
    Token result = this.look;
    this.look = this.lexer.readToken();
    return result;
  }

  /**
   * Reinicia o parser
   */
  public void reset() {
    this.look = null;
  }

}
