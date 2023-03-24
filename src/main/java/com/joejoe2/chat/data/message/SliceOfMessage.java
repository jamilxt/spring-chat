package com.joejoe2.chat.data.message;

import com.joejoe2.chat.data.SliceList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SliceOfMessage<E extends MessageDto> {
  private int currentPage;
  private int pageSize;
  private boolean hasNext;
  private List<E> messages;

  public SliceOfMessage(SliceList<E> sliceList) {
    this.currentPage = sliceList.getCurrentPage();
    this.pageSize = sliceList.getPageSize();
    this.hasNext = sliceList.isHasNext();
    this.messages = sliceList.getList();
  }
}
