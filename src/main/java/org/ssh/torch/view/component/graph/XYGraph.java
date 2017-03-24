package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author jeroen.dejong
 * @since 07/02/2017.
 */
public abstract class XYGraph<G extends XYGraph> extends AbstractComponent<G> {
    @Setter
    Integer maxY, minY;
    @Setter
    String xLabel = "time", yLabel = "sinus";
    @Override
    protected ComponentRenderer<G> createDefaultRenderer() {
        return new DefaultXYGraphRender();
    }
    
    private class DefaultXYGraphRender implements ComponentRenderer<G> {
        @Override
        public TerminalSize getPreferredSize(XYGraph component) {
            return new TerminalSize(20, 10);
        }

        @Override
        public void drawComponent(TextGUIGraphics graphics, G component) {
            TerminalSize graphSize = new TerminalSize(
                    component.getPreferredSize().getColumns() -1,
                    component.getPreferredSize().getRows() -1);
            int start = Math.max(0, component.getData().size()-graphSize.getColumns());
            int stop  = Math.max(0, component.getData().size());
            graphics.setBackgroundColor(new TextColor.RGB(150, 150, 150));
            graphics.fillRectangle(getPosition(), graphSize, ' ');
            List<Map.Entry<Integer, Integer>> test = component.getData().subList(start, stop);
            test.forEach(entry -> graphics.setCharacter(entry.getKey() - start, entry.getValue()-1, '*'));
            graphics.putString(component.getPosition().getColumn(),
                    component.getPosition().getRow() + component.getPreferredSize().getRows()-1,
                    getXLabel(component.getPreferredSize().getColumns()-1));
        }
    }

    private String getXLabel(int width){
        return String.format("<%1$" + (width-1) + "s", xLabel).replace(' ', '-');
    }

    protected abstract List<Map.Entry<Integer, Integer>> getData();
}
