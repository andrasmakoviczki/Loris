package trash;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.elte.spring.loris.backend.service.CategoryService;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.service.TopicService;
import edu.elte.spring.loris.backend.service.UserService;

public class GetService {

	public GetService(){
		//this.type = type;
	}
	
	/*private static HttpSession getCurrentSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	public static CategoryService GetCategoryService(){
		//HttpSession session = getCurrentSession();
		CategoryService caService = null; //= (CategoryService) session.getAttribute("caService");
		//if (caService == null)
        //{
			ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
			caService = (CategoryService)context.getBean("CategoryService");
            //session.setAttribute("caService", caService);
       // }
		return caService;
	}
	
	public static ChannelService GetChannelService(){
		//HttpSession session = getCurrentSession();
		ChannelService chService = null; //= (ChannelService) session.getAttribute("chService");
		//if (chService == null)
        //{
		ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
		chService = (ChannelService)context.getBean("ChannelService");
            //session.setAttribute("chService", chService);
        //}
		return chService;
	}
	
	public static FeedEntryService GetFeedEntryService(){
		//HttpSession session = getCurrentSession();
		FeedEntryService feService = null; // = (FeedEntryService) session.getAttribute("feService");
		//if (feService == null)
        //{
		
		ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
		feService = (FeedEntryService)context.getBean("FeedEntryService");
            //session.setAttribute("feService", feService);
        //}
		return feService;
	}
	
	public static SubscriptionService GetSubscriptionService(){
		//HttpSession session = getCurrentSession();
		SubscriptionService sService = null;// = (SubscriptionService) session.getAttribute("sService");
		//if (sService == null)
        //{
			ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
			sService = (SubscriptionService) context.getBean("SubscriptionService");
            //session.setAttribute("sService", sService);
        //}
		return sService;
	}
	
	public static TopicService GetTopicService(){
		//HttpSession session = getCurrentSession();
		TopicService tService = null;// = (TopicService) session.getAttribute("tService");
		//if (tService == null)
        //{
			ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
			tService = (TopicService) context.getBean("TopicService");
            //session.setAttribute("tService", tService);
        //}
		return tService;
	}
	
	public static UserService GetUserService(){
		HttpSession session = getCurrentSession();
		UserService uService = (UserService) session.getAttribute("uService");
		if (uService == null)
        {
			ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/andris/workspace/Loris/src/main/webapp/WEB-INF/spring/appServlet/kundera-context.xml");
//			Object o = context.getBean("userdao");
			uService = (UserService) context.getBean("UserService");
            session.setAttribute("uService", uService);
        }
		return uService;
	}*/

}
