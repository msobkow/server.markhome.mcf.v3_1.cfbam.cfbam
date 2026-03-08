// Description: Java 25 Table Object implementation for SchemaRef.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamSchemaRefTableObj
	implements ICFBamSchemaRefTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamSchemaRef.CLASS_CODE;
	protected static final int backingClassCode = ICFBamSchemaRef.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> members;
	private Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> allSchemaRef;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > > indexByTenantIdx;
	private Map< ICFBamSchemaRefBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > > indexBySchemaIdx;
	private Map< ICFBamSchemaRefByUNameIdxKey,
		ICFBamSchemaRefObj > indexByUNameIdx;
	private Map< ICFBamSchemaRefByRefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > > indexByRefSchemaIdx;
	private Map< ICFBamSchemaRefByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > > indexByPrevIdx;
	private Map< ICFBamSchemaRefByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > > indexByNextIdx;
	public static String TABLE_NAME = "SchemaRef";
	public static String TABLE_DBNAME = "schema_ref";

	public CFBamSchemaRefTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
		allSchemaRef = null;
		indexByTenantIdx = null;
		indexBySchemaIdx = null;
		indexByUNameIdx = null;
		indexByRefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}

	public CFBamSchemaRefTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
		allSchemaRef = null;
		indexByTenantIdx = null;
		indexBySchemaIdx = null;
		indexByUNameIdx = null;
		indexByRefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamSchemaRefTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamSchemaRefTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSchemaRef = null;
		indexByTenantIdx = null;
		indexBySchemaIdx = null;
		indexByUNameIdx = null;
		indexByRefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
		ICFBamSchemaRefObj cur = null;
		Iterator<ICFBamSchemaRefObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaRefObj.
	 */
	@Override
	public ICFBamSchemaRefObj newInstance() {
		ICFBamSchemaRefObj inst = new CFBamSchemaRefObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaRefObj.
	 */
	@Override
	public ICFBamSchemaRefEditObj newEditInstance( ICFBamSchemaRefObj orig ) {
		ICFBamSchemaRefEditObj edit = new CFBamSchemaRefEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamSchemaRefObj realiseSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaRefObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamSchemaRefObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRefBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.remove( keepObj.getPKey() );
					if( mapSchemaIdx.size() <= 0 ) {
						indexBySchemaIdx.remove( keySchemaIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaRefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByRefSchemaIdx != null ) {
				ICFBamSchemaRefByRefSchemaIdxKey keyRefSchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
				keyRefSchemaIdx.setOptionalRefSchemaId( keepObj.getOptionalRefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapRefSchemaIdx = indexByRefSchemaIdx.get( keyRefSchemaIdx );
				if( mapRefSchemaIdx != null ) {
					mapRefSchemaIdx.remove( keepObj.getPKey() );
					if( mapRefSchemaIdx.size() <= 0 ) {
						indexByRefSchemaIdx.remove( keyRefSchemaIdx );
					}
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamSchemaRefByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamSchemaRefByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamSchemaRefObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRefBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaRefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRefSchemaIdx != null ) {
				ICFBamSchemaRefByRefSchemaIdxKey keyRefSchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
				keyRefSchemaIdx.setOptionalRefSchemaId( keepObj.getOptionalRefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapRefSchemaIdx = indexByRefSchemaIdx.get( keyRefSchemaIdx );
				if( mapRefSchemaIdx != null ) {
					mapRefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamSchemaRefByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamSchemaRefByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSchemaRef != null ) {
				allSchemaRef.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamSchemaRefObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSchemaRef != null ) {
				allSchemaRef.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRefBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaRefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaId( keepObj.getRequiredSchemaId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRefSchemaIdx != null ) {
				ICFBamSchemaRefByRefSchemaIdxKey keyRefSchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
				keyRefSchemaIdx.setOptionalRefSchemaId( keepObj.getOptionalRefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapRefSchemaIdx = indexByRefSchemaIdx.get( keyRefSchemaIdx );
				if( mapRefSchemaIdx != null ) {
					mapRefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamSchemaRefByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamSchemaRefByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRefObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamSchemaRefObj createSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = Obj;
		ICFBamSchemaRef rec = obj.getSchemaRefRec();
		schema.getCFBamBackingStore().getTableSchemaRef().createSchemaRef(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamSchemaRefObj)(obj.realise());
		}
		ICFBamSchemaRefObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRef( CFLibDbKeyHash256 pkey ) {
		return( readSchemaRef( pkey, false ) );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRef( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamSchemaRefObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamSchemaRef readRec = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamSchemaRefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamSchemaRefObj readCachedSchemaRef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRefObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSchemaRef( ICFBamSchemaRefObj obj )
	{
		final String S_ProcName = "CFBamSchemaRefTableObj.reallyDeepDisposeSchemaRef() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaRefObj existing = readCachedSchemaRef( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamSchemaRefBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
		keySchemaIdx.setRequiredSchemaId( existing.getRequiredSchemaId() );

		ICFBamSchemaRefByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
		keyUNameIdx.setRequiredSchemaId( existing.getRequiredSchemaId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamSchemaRefByRefSchemaIdxKey keyRefSchemaIdx = schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
		keyRefSchemaIdx.setOptionalRefSchemaId( existing.getOptionalRefSchemaId() );

		ICFBamSchemaRefByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamSchemaRefByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );



		if( indexBySchemaIdx != null ) {
			if( indexBySchemaIdx.containsKey( keySchemaIdx ) ) {
				indexBySchemaIdx.get( keySchemaIdx ).remove( pkey );
				if( indexBySchemaIdx.get( keySchemaIdx ).size() <= 0 ) {
					indexBySchemaIdx.remove( keySchemaIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByRefSchemaIdx != null ) {
			if( indexByRefSchemaIdx.containsKey( keyRefSchemaIdx ) ) {
				indexByRefSchemaIdx.get( keyRefSchemaIdx ).remove( pkey );
				if( indexByRefSchemaIdx.get( keyRefSchemaIdx ).size() <= 0 ) {
					indexByRefSchemaIdx.remove( keyRefSchemaIdx );
				}
			}
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeSchemaRef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRefObj obj = readCachedSchemaRef( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamSchemaRefObj lockSchemaRef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRefObj locked = null;
		ICFBamSchemaRef lockRec = schema.getCFBamBackingStore().getTableSchemaRef().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamSchemaRefObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSchemaRef", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamSchemaRefObj> readAllSchemaRef() {
		return( readAllSchemaRef( false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readAllSchemaRef( boolean forceRead ) {
		final String S_ProcName = "readAllSchemaRef";
		if( ( allSchemaRef == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> map = new HashMap<CFLibDbKeyHash256,ICFBamSchemaRefObj>();
			allSchemaRef = map;
			ICFBamSchemaRef[] recList = schema.getCFBamBackingStore().getTableSchemaRef().readAllDerived( null );
			ICFBamSchemaRef rec;
			ICFBamSchemaRefObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
			}
		}
		int len = allSchemaRef.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = allSchemaRef.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedAllSchemaRef() {
		final String S_ProcName = "readCachedAllSchemaRef";
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( allSchemaRef != null ) {
			int len = allSchemaRef.size();
			ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
			Iterator<ICFBamSchemaRefObj> valIter = allSchemaRef.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRefByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readSchemaRefByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRefByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamSchemaRefObj obj = readSchemaRef( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readSchemaRefByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRefByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId )
	{
		return( readSchemaRefBySchemaIdx( SchemaId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRefBySchemaIdx";
		ICFBamSchemaRefBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
		key.setRequiredSchemaId( SchemaId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaRefBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
			ICFBamSchemaRefObj obj;
			ICFBamSchemaRef[] recList = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedBySchemaIdx( null,
				SchemaId );
			ICFBamSchemaRef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name )
	{
		return( readSchemaRefByUNameIdx( SchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamSchemaRefObj readSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamSchemaRefByUNameIdxKey,
				ICFBamSchemaRefObj >();
		}
		ICFBamSchemaRefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
		key.setRequiredSchemaId( SchemaId );
		key.setRequiredName( Name );
		ICFBamSchemaRefObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamSchemaRef rec = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedByUNameIdx( null,
				SchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaRefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId )
	{
		return( readSchemaRefByRefSchemaIdx( RefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRefByRefSchemaIdx";
		ICFBamSchemaRefByRefSchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
		key.setOptionalRefSchemaId( RefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
		if( indexByRefSchemaIdx == null ) {
			indexByRefSchemaIdx = new HashMap< ICFBamSchemaRefByRefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( ( ! forceRead ) && indexByRefSchemaIdx.containsKey( key ) ) {
			dict = indexByRefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
			ICFBamSchemaRefObj obj;
			ICFBamSchemaRef[] recList = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedByRefSchemaIdx( null,
				RefSchemaId );
			ICFBamSchemaRef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readSchemaRefByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRefByPrevIdx";
		ICFBamSchemaRefByPrevIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamSchemaRefByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
			ICFBamSchemaRefObj obj;
			ICFBamSchemaRef[] recList = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamSchemaRef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readSchemaRefByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRefObj> readSchemaRefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRefByNextIdx";
		ICFBamSchemaRefByNextIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamSchemaRefByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRefObj>();
			ICFBamSchemaRefObj obj;
			ICFBamSchemaRef[] recList = schema.getCFBamBackingStore().getTableSchemaRef().readDerivedByNextIdx( null,
				NextId );
			ICFBamSchemaRef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRefObj realised = (ICFBamSchemaRefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
		Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaRefObj readCachedSchemaRefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRefObj obj = null;
		obj = readCachedSchemaRef( Id );
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedSchemaRefByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
				Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRefObj obj;
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId )
	{
		final String S_ProcName = "readCachedSchemaRefBySchemaIdx";
		ICFBamSchemaRefBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
		key.setRequiredSchemaId( SchemaId );
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
				Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRefObj obj;
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaRefObj readCachedSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name )
	{
		ICFBamSchemaRefObj obj = null;
		ICFBamSchemaRefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
		key.setRequiredSchemaId( SchemaId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId )
	{
		final String S_ProcName = "readCachedSchemaRefByRefSchemaIdx";
		ICFBamSchemaRefByRefSchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
		key.setOptionalRefSchemaId( RefSchemaId );
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( indexByRefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
			if( indexByRefSchemaIdx.containsKey( key ) ) {
				dict = indexByRefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
				Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRefObj obj;
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedSchemaRefByPrevIdx";
		ICFBamSchemaRefByPrevIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
				Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRefObj obj;
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaRefObj> readCachedSchemaRefByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedSchemaRefByNextIdx";
		ICFBamSchemaRefByNextIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamSchemaRefObj> arrayList = new ArrayList<ICFBamSchemaRefObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRefObj arr[] = new ICFBamSchemaRefObj[len];
				Iterator<ICFBamSchemaRefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRefObj obj;
			Iterator<ICFBamSchemaRefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRefObj> cmp = new Comparator<ICFBamSchemaRefObj>() {
			@Override
			public int compare( ICFBamSchemaRefObj lhs, ICFBamSchemaRefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSchemaRefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRefObj obj = readCachedSchemaRefByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeSchemaRefByTenantIdx";
		ICFBamSchemaRefObj obj;
		List<ICFBamSchemaRefObj> arrayList = readCachedSchemaRefByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId )
	{
		final String S_ProcName = "deepDisposeSchemaRefBySchemaIdx";
		ICFBamSchemaRefObj obj;
		List<ICFBamSchemaRefObj> arrayList = readCachedSchemaRefBySchemaIdx( SchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name )
	{
		ICFBamSchemaRefObj obj = readCachedSchemaRefByUNameIdx( SchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId )
	{
		final String S_ProcName = "deepDisposeSchemaRefByRefSchemaIdx";
		ICFBamSchemaRefObj obj;
		List<ICFBamSchemaRefObj> arrayList = readCachedSchemaRefByRefSchemaIdx( RefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeSchemaRefByPrevIdx";
		ICFBamSchemaRefObj obj;
		List<ICFBamSchemaRefObj> arrayList = readCachedSchemaRefByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRefByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeSchemaRefByNextIdx";
		ICFBamSchemaRefObj obj;
		List<ICFBamSchemaRefObj> arrayList = readCachedSchemaRefByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamSchemaRefObj updateSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaRef().updateSchemaRef( null,
			Obj.getSchemaRefRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().getClassCode() ) {
			obj = (ICFBamSchemaRefObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = Obj;
		ICFBamSchemaRefObj prev = obj.getOptionalLookupPrev();
		ICFBamSchemaRefObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRef( null,
			obj.getSchemaRefRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteSchemaRefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRefObj obj = readSchemaRef(Id);
		if( obj != null ) {
			ICFBamSchemaRefEditObj editObj = (ICFBamSchemaRefEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamSchemaRefEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSchemaRefByIdIdx( Id );
	}

	@Override
	public void deleteSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByTenantIdx( null,
				TenantId );
			Iterator<ICFBamSchemaRefObj> iter = dict.values().iterator();
			ICFBamSchemaRefObj obj;
			List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByTenantIdx( null,
				TenantId );
		}
		deepDisposeSchemaRefByTenantIdx( TenantId );
	}

	@Override
	public void deleteSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId )
	{
		ICFBamSchemaRefBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newBySchemaIdxKey();
		key.setRequiredSchemaId( SchemaId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaRefBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefBySchemaIdx( null,
				SchemaId );
			Iterator<ICFBamSchemaRefObj> iter = dict.values().iterator();
			ICFBamSchemaRefObj obj;
			List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefBySchemaIdx( null,
				SchemaId );
		}
		deepDisposeSchemaRefBySchemaIdx( SchemaId );
	}

	@Override
	public void deleteSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamSchemaRefByUNameIdxKey,
				ICFBamSchemaRefObj >();
		}
		ICFBamSchemaRefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByUNameIdxKey();
		key.setRequiredSchemaId( SchemaId );
		key.setRequiredName( Name );
		ICFBamSchemaRefObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByUNameIdx( null,
				SchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByUNameIdx( null,
				SchemaId,
				Name );
		}
		deepDisposeSchemaRefByUNameIdx( SchemaId,
				Name );
	}

	@Override
	public void deleteSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId )
	{
		ICFBamSchemaRefByRefSchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByRefSchemaIdxKey();
		key.setOptionalRefSchemaId( RefSchemaId );
		if( indexByRefSchemaIdx == null ) {
			indexByRefSchemaIdx = new HashMap< ICFBamSchemaRefByRefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( indexByRefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict = indexByRefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByRefSchemaIdx( null,
				RefSchemaId );
			Iterator<ICFBamSchemaRefObj> iter = dict.values().iterator();
			ICFBamSchemaRefObj obj;
			List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByRefSchemaIdx( null,
				RefSchemaId );
		}
		deepDisposeSchemaRefByRefSchemaIdx( RefSchemaId );
	}

	@Override
	public void deleteSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamSchemaRefByPrevIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamSchemaRefByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByPrevIdx( null,
				PrevId );
			Iterator<ICFBamSchemaRefObj> iter = dict.values().iterator();
			ICFBamSchemaRefObj obj;
			List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByPrevIdx( null,
				PrevId );
		}
		deepDisposeSchemaRefByPrevIdx( PrevId );
	}

	@Override
	public void deleteSchemaRefByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamSchemaRefByNextIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRef().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamSchemaRefByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRefObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRefObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByNextIdx( null,
				NextId );
			Iterator<ICFBamSchemaRefObj> iter = dict.values().iterator();
			ICFBamSchemaRefObj obj;
			List<ICFBamSchemaRefObj> toForget = new LinkedList<ICFBamSchemaRefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRef().deleteSchemaRefByNextIdx( null,
				NextId );
		}
		deepDisposeSchemaRefByNextIdx( NextId );
	}

	/**
	 *	Move the CFBamSchemaRefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamSchemaRefObj refreshed cache instance.
	 */
	@Override
	public ICFBamSchemaRefObj moveUpSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpSchemaRef" );
		}
		ICFBamSchemaRef rec = schema.getCFBamBackingStore().getTableSchemaRef().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getSchemaRefTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamSchemaRefObj)obj.realise();
			ICFBamSchemaRefObj prev = obj.getOptionalLookupPrev( true );
			ICFBamSchemaRefObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamSchemaRefObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamSchemaRefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamSchemaRefObj refreshed cache instance.
	 */
	@Override
	public ICFBamSchemaRefObj moveDownSchemaRef( ICFBamSchemaRefObj Obj ) {
		ICFBamSchemaRefObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownSchemaRef" );
		}
		ICFBamSchemaRef rec = schema.getCFBamBackingStore().getTableSchemaRef().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getSchemaRefTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamSchemaRefObj)obj.realise();
			ICFBamSchemaRefObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamSchemaRefObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamSchemaRefObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}