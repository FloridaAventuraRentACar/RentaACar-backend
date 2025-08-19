package backend.car_rental.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PaginatedData<T> {
    private final List<T> results;
    private final int count;
    private final int totalPages;
    private final int currentPage;
    private final int pageSize;
}