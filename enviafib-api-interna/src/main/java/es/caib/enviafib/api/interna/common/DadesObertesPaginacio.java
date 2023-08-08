package es.caib.enviafib.api.interna.common;

import java.util.List;

/**
 * 
 * @author anadal
 *
 * @param <D>
 */
public class DadesObertesPaginacio<D> {

    protected int pagesize;
    protected int page;
    protected int totalpages;
    protected int totalcount;
    protected List<D> data;

    public DadesObertesPaginacio() {
        super();
    }

    public DadesObertesPaginacio(int pagesize, int page, int totalpages, int totalcount, List<D> data) {
        super();
        this.pagesize = pagesize;
        this.page = page;
        this.totalpages = totalpages;
        this.totalcount = totalcount;
        this.data = data;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

}
