//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.alipaydemo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;
    private Long totalRows;
    private int numPerPage;
    private int currentPage;
    private int totalPages;
    private List<T> resultList = new ArrayList();

    public static <T> PageResultBuilder<T> builder() {
        return new PageResultBuilder();
    }

    public Long getTotalRows() {
        return this.totalRows;
    }

    public int getNumPerPage() {
        return this.numPerPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public List<T> getResultList() {
        return this.resultList;
    }

    public void setTotalRows(final Long totalRows) {
        this.totalRows = totalRows;
    }

    public void setNumPerPage(final int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public void setResultList(final List<T> resultList) {
        this.resultList = resultList;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getNumPerPage() != other.getNumPerPage()) {
                return false;
            } else if (this.getCurrentPage() != other.getCurrentPage()) {
                return false;
            } else if (this.getTotalPages() != other.getTotalPages()) {
                return false;
            } else {
                Object this$totalRows = this.getTotalRows();
                Object other$totalRows = other.getTotalRows();
                if (this$totalRows == null) {
                    if (other$totalRows != null) {
                        return false;
                    }
                } else if (!this$totalRows.equals(other$totalRows)) {
                    return false;
                }

                Object this$resultList = this.getResultList();
                Object other$resultList = other.getResultList();
                if (this$resultList == null) {
                    if (other$resultList != null) {
                        return false;
                    }
                } else if (!this$resultList.equals(other$resultList)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getNumPerPage();
        result = result * 59 + this.getCurrentPage();
        result = result * 59 + this.getTotalPages();
        Object $totalRows = this.getTotalRows();
        result = result * 59 + ($totalRows == null ? 43 : $totalRows.hashCode());
        Object $resultList = this.getResultList();
        result = result * 59 + ($resultList == null ? 43 : $resultList.hashCode());
        return result;
    }

    public String toString() {
        return "PageResult(totalRows=" + this.getTotalRows() + ", numPerPage=" + this.getNumPerPage() + ", currentPage=" + this.getCurrentPage() + ", totalPages=" + this.getTotalPages() + ", resultList=" + this.getResultList() + ")";
    }

    public PageResult() {
    }

    public PageResult(final Long totalRows, final int numPerPage, final int currentPage, final int totalPages, final List<T> resultList) {
        this.totalRows = totalRows;
        this.numPerPage = numPerPage;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.resultList = resultList;
    }

    public static class PageResultBuilder<T> {
        private Long totalRows;
        private int numPerPage;
        private int currentPage;
        private int totalPages;
        private List<T> resultList;

        PageResultBuilder() {
        }

        public PageResultBuilder<T> totalRows(final Long totalRows) {
            this.totalRows = totalRows;
            return this;
        }

        public PageResultBuilder<T> numPerPage(final int numPerPage) {
            this.numPerPage = numPerPage;
            return this;
        }

        public PageResultBuilder<T> currentPage(final int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public PageResultBuilder<T> totalPages(final int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PageResultBuilder<T> resultList(final List<T> resultList) {
            this.resultList = resultList;
            return this;
        }

        public PageResult<T> build() {
            return new PageResult(this.totalRows, this.numPerPage, this.currentPage, this.totalPages, this.resultList);
        }

        public String toString() {
            return "PageResult.PageResultBuilder(totalRows=" + this.totalRows + ", numPerPage=" + this.numPerPage + ", currentPage=" + this.currentPage + ", totalPages=" + this.totalPages + ", resultList=" + this.resultList + ")";
        }
    }
}
