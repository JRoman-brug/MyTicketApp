import { Pagination } from '@heroui/react';

interface MoviePaginationProps {
  readonly currentPage: number;
  readonly setCurrentPage: (page: number) => void;
  readonly totalPage: number;
}

function MoviePagination({
  currentPage,
  setCurrentPage,
  totalPage,
}: MoviePaginationProps) {
  return (
    <Pagination
      page={currentPage}
      onChange={setCurrentPage}
      initialPage={1}
      total={totalPage}
    />
  );
}

export default MoviePagination;
