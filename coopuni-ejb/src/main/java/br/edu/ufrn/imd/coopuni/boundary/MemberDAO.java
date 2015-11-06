package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;

public interface MemberDAO extends AbstractDAO<Long, Member> {
  public void delete(Member entity) throws Exception;

  public Member findByEmail(String email);

  public Member findByUsername(String username);
}
