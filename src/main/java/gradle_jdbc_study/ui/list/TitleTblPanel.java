package gradle_jdbc_study.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_study.dto.Title;

@SuppressWarnings("serial")
public class TitleTblPanel extends AbstractTblPanel<Title> {
	public TitleTblPanel() {
	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150);
		tableCellAlign(SwingConstants.CENTER, 0, 1);
		
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"번호", "직책명"};
	}

	@Override
	protected Object[] toArray(Title item) {
		return new Object[] {item.getTitleNo(),	item.getTitleName()};
	}

	@Override
	public void updateRow(Title item, int updateIdx) {
		model.setValueAt(item.getTitleNo(), updateIdx, 0);
		model.setValueAt(item.getTitleName(), updateIdx, 1);
	}

}
