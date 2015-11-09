package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;

public interface MemberDAO extends AbstractDAO<Long, Member> {
  void delete(Member entity) throws Exception;

  Member findByEmail(String email);

  Member findByUsername(String username);
}
