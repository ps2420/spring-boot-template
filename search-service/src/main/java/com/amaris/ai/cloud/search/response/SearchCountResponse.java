package com.amaris.ai.cloud.search.response;

import java.io.Serializable;

public class SearchCountResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer count;
  private IndexShard _shards;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public IndexShard get_shards() {
    return _shards;
  }

  public void set_shards(IndexShard _shards) {
    this._shards = _shards;
  }

  public static class IndexShard {

    private Integer total;
    private Integer successful;
    private Integer skipped;
    private Integer failed;

    public Integer getTotal() {
      return total;
    }

    public void setTotal(Integer total) {
      this.total = total;
    }

    public Integer getSuccessful() {
      return successful;
    }

    public void setSuccessful(Integer successful) {
      this.successful = successful;
    }

    public Integer getSkipped() {
      return skipped;
    }

    public void setSkipped(Integer skipped) {
      this.skipped = skipped;
    }

    public Integer getFailed() {
      return failed;
    }

    public void setFailed(Integer failed) {
      this.failed = failed;
    }
  }

}
