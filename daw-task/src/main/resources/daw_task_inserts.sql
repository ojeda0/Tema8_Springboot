use daw_task;

INSERT INTO tarea (titulo, descripcion, fecha_creacion, fecha_vencimiento, estado) VALUES
('Configurar entorno', 'Instalar Docker y levantar contenedores locales', '2026-03-01', '2026-03-05', 'COMPLETADA'),
('Migración de registros', 'Pasar el histórico de usuarios del sistema antiguo al nuevo', '2026-03-10', '2026-03-15', 'COMPLETADA'),
('Implementar autenticación', 'Añadir tokens JWT a los endpoints de la API principal', '2026-03-20', '2026-03-28', 'EN_PROGRESO'),
('Optimizar búsquedas', 'Revisar y añadir índices a las queries lentas en los reportes', '2026-03-22', '2026-03-30', 'PENDIENTE'),
('Ampliar tests unitarios', 'Alcanzar el 80% de cobertura en la capa de servicios', '2026-03-23', '2026-04-05', 'EN_PROGRESO'),
('Actualizar manuales', 'Redactar la wiki con instrucciones de despliegue a producción', '2026-03-24', '2026-03-26', 'PENDIENTE'),
('Revisión de código', 'Comprobar las solicitudes de extracción pendientes del equipo', '2026-03-25', '2026-03-25', 'PENDIENTE'),
('Auditoría de seguridad', 'Buscar dependencias vulnerables con la herramienta de OWASP', '2026-03-18', '2026-03-20', 'COMPLETADA'),
('Diseño de vistas', 'Crear los bocetos iniciales para el panel de administración', '2026-03-12', '2026-03-19', 'COMPLETADA'),
('Despliegue automático', 'Crear flujos de trabajo en el sistema de integración continua', '2026-03-21', '2026-04-02', 'EN_PROGRESO'),
('Integrar facturación', 'Conectar el proveedor de pagos para las suscripciones', '2026-03-25', '2026-04-10', 'PENDIENTE'),
('Corregir acceso fallido', 'Arreglar el cuelgue de la app al usar caracteres especiales en el login', '2026-03-24', '2026-03-25', 'EN_PROGRESO'),
('Preparar demostración', 'Crear las diapositivas para la reunión final con el cliente', '2026-03-15', '2026-03-22', 'COMPLETADA'),
('Añadir métricas', 'Configurar alertas de memoria y CPU en el panel de control', '2026-03-26', '2026-04-08', 'PENDIENTE'),
('Mantenimiento de BD', 'Purgar tablas temporales y limpiar registros huérfanos', '2026-03-25', '2026-03-31', 'PENDIENTE');