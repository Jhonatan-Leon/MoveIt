package uniquindio.Model.DTO;

import java.util.ArrayList;
import java.util.List;

public class AdminMetricasDTO {
    private double tiempoPromedioEntregaHoras;
    private List<MetricDataPointDTO> ingresosPorPeriodo;

    public AdminMetricasDTO() {
        this.ingresosPorPeriodo = new ArrayList<>();
    }

    public double getTiempoPromedioEntregaHoras() {
        return tiempoPromedioEntregaHoras;
    }

    public void setTiempoPromedioEntregaHoras(double tiempoPromedioEntregaHoras) {
        this.tiempoPromedioEntregaHoras = tiempoPromedioEntregaHoras;
    }

    public List<MetricDataPointDTO> getIngresosPorPeriodo() {
        return ingresosPorPeriodo;
    }

    public void setIngresosPorPeriodo(List<MetricDataPointDTO> ingresosPorPeriodo) {
        this.ingresosPorPeriodo = ingresosPorPeriodo;
    }
}

