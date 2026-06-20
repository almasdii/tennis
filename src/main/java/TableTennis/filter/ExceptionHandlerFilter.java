package TableTennis.filter;

import TableTennis.Exception.*;
import TableTennis.utils.JspHelper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class ExceptionHandlerFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            log.debug("Exception handler Filter starts, before doFilter");
            chain.doFilter(httpServletRequest, httpServletResponse);
            log.debug("after doFilter");
        }catch (PlayerSearchValidationException exception){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletRequest.setAttribute("error", exception.getMessage());
            log.warn("Error ",exception);
            httpServletRequest.getRequestDispatcher(JspHelper.getPath("matches")).forward(httpServletRequest, httpServletResponse);
        }
        catch (ValidationException exception) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletRequest.setAttribute("error", exception.getMessage());
            log.warn("Error ",exception);
            httpServletRequest.getRequestDispatcher(JspHelper.getPath("new-match")).forward(httpServletRequest, httpServletResponse);
        } catch (BadRequestException exception) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletRequest.setAttribute("error", exception.getMessage());
            log.error("Error ",exception);
            httpServletRequest.getRequestDispatcher("/error").forward(httpServletRequest, httpServletResponse);
        } catch (MatchNotFoundException exception) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpServletRequest.setAttribute("error", exception.getMessage());
            log.warn("Error ",exception);
            httpServletRequest.getRequestDispatcher("/error").forward(httpServletRequest, httpServletResponse);
        }catch (DataBaseException exception){
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletRequest.setAttribute("error", "Error with Db");
            log.error("Error ",exception);
            httpServletRequest.getRequestDispatcher("/error").forward(httpServletRequest, httpServletResponse);
        }
        catch (Exception exception) {
            log.error("Exception ", exception);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletRequest.setAttribute("error", "Unexpected Error");
            httpServletRequest.getRequestDispatcher("/error").forward(httpServletRequest, httpServletResponse);
        }
    }

}
