package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 메모리니까 어딘가에 저장해야된다
    // 실무에서는 동시성 문제가 있어서 공유되는 변수일때는 ConcurrentHashMap 을 써야 하지만
    // 간단한 예제니까 여기서는 HashMap을 씀 => 그래서 클라이언트에서 작업을 할수가 있다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {

        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

    // 자바 강의 아니니까 라무다를 씀 (루프 돌림)
    // member.getName() 이 파라미터 name 이랑 같은지 확인한다
    // 같은 경우에만 필터링이되고 그중에서 찾으면 반환한다.

        store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        return Optional.empty();
    }

    // store.values()를 리스트에 반환
    // store.values() = member
    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    // 여기까지 구현 끝. 이제 확인을 위해 테스트케이스를 작성한다
}
